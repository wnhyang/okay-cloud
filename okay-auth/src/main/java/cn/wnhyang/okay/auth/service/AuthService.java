package cn.wnhyang.okay.auth.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.wnhyang.okay.auth.vo.AuthLoginReqVO;
import cn.wnhyang.okay.auth.vo.RegisterReqVO;
import cn.wnhyang.okay.framework.common.enums.CommonStatusEnum;
import cn.wnhyang.okay.framework.common.enums.DeviceTypeEnum;
import cn.wnhyang.okay.framework.common.enums.UserTypeEnum;
import cn.wnhyang.okay.framework.common.util.ServletUtils;
import cn.wnhyang.okay.framework.satoken.utils.LoginHelper;
import cn.wnhyang.okay.system.api.LoginLogApi;
import cn.wnhyang.okay.system.api.UserApi;
import cn.wnhyang.okay.system.dto.LoginUser;
import cn.wnhyang.okay.system.dto.loginlog.LoginLogCreateReqDTO;
import cn.wnhyang.okay.system.dto.user.UserCreateReqDTO;
import cn.wnhyang.okay.system.enums.login.LoginResultEnum;
import cn.wnhyang.okay.system.enums.login.LoginTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.system.enums.ErrorCodeConstants.AUTH_LOGIN_BAD_CREDENTIALS;
import static cn.wnhyang.okay.system.enums.ErrorCodeConstants.AUTH_LOGIN_USER_DISABLED;

/**
 * @author wnhyang
 * @date 2023/7/26
 **/
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserApi userApi;

    private final LoginLogApi loginLogApi;

    public String login(AuthLoginReqVO reqVO) {
        String username = reqVO.getUsername();
        String mobile = reqVO.getMobile();
        String email = reqVO.getEmail();
        LoginUser user;
        LoginTypeEnum loginTypeEnum;
        if (StrUtil.isNotEmpty(username)) {
            user = userApi.getUserInfo(username, null, null).getCheckedData();
            loginTypeEnum = LoginTypeEnum.LOGIN_USERNAME;
        } else if (StrUtil.isNotEmpty(mobile)) {
            user = userApi.getUserInfo(null, mobile, null).getCheckedData();
            loginTypeEnum = LoginTypeEnum.LOGIN_MOBILE;
        } else if (StrUtil.isNotEmpty(email)) {
            user = userApi.getUserInfo(null, null, email).getCheckedData();
            loginTypeEnum = LoginTypeEnum.LOGIN_EMAIL;
        } else {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!BCrypt.checkpw(reqVO.getPassword(), user.getPassword())) {
            createLoginLog(user.getId(), username, loginTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            createLoginLog(user.getId(), username, loginTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }

        // 创建 Token 令牌，记录登录日志
        LoginHelper.login(user, DeviceTypeEnum.PC);
        createLoginLog(user.getId(), username, LoginTypeEnum.LOGIN_USERNAME, LoginResultEnum.SUCCESS);
        return StpUtil.getTokenValue();
    }

    public void logout() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        if (loginUser != null) {
            StpUtil.logout();
            createLoginLog(loginUser.getId(), loginUser.getUsername(), LoginTypeEnum.LOGOUT_SELF, LoginResultEnum.SUCCESS);
        }
    }

    public void register(RegisterReqVO reqVO) {
        String username = reqVO.getUsername();
        String password = reqVO.getPassword();
        Integer userType = UserTypeEnum.valueOf(reqVO.getUserType()).getType();
        UserCreateReqDTO reqDTO = new UserCreateReqDTO();
        reqDTO.setUsername(username);
        reqDTO.setNickname(username);
        reqDTO.setPassword(BCrypt.hashpw(password));
        reqDTO.setType(userType);
        userApi.registerUser(reqDTO);
    }

    private void createLoginLog(Long userId, String username, LoginTypeEnum loginTypeEnum, LoginResultEnum loginResultEnum) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLoginType(loginTypeEnum.getType());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(UserTypeEnum.PC.getType());
        reqDTO.setUsername(username);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(loginResultEnum.getResult());
        loginLogApi.createLoginLog(reqDTO);
        // 更新最后登录时间
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResultEnum.getResult())) {
            userApi.updateUserLogin(userId, ServletUtils.getClientIP());
        }
    }
}
