package cn.wnhyang.okay.auth.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.wnhyang.okay.auth.redis.RedisKeyConstants;
import cn.wnhyang.okay.auth.vo.EmailLoginReqVO;
import cn.wnhyang.okay.auth.vo.LoginReqVO;
import cn.wnhyang.okay.auth.vo.RegisterReqVO;
import cn.wnhyang.okay.framework.common.enums.CommonStatusEnum;
import cn.wnhyang.okay.framework.common.enums.DeviceTypeEnum;
import cn.wnhyang.okay.framework.common.enums.UserTypeEnum;
import cn.wnhyang.okay.framework.common.util.RegexUtils;
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
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.system.enums.ErrorCodeConstants.*;

/**
 * @author wnhyang
 * @date 2023/7/26
 **/
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserApi userApi;

    private final LoginLogApi loginLogApi;

    private final ValueOperations<String, String> valueOperations;

    public String login(LoginReqVO reqVO) {
        String account = reqVO.getAccount();
        LoginUser user;
        LoginTypeEnum loginTypeEnum;
        if (StrUtil.isNotEmpty(account)) {
            if (ReUtil.isMatch(RegexUtils.MOBILE, account)) {
                user = userApi.getUserInfo(account, account, "").getCheckedData();
                loginTypeEnum = LoginTypeEnum.LOGIN_MOBILE;
            } else if (ReUtil.isMatch(RegexUtils.EMAIL, account)) {
                user = userApi.getUserInfo(account, "", account).getCheckedData();
                loginTypeEnum = LoginTypeEnum.LOGIN_EMAIL;
            } else {
                user = userApi.getUserInfo(account, "", "").getCheckedData();
                loginTypeEnum = LoginTypeEnum.LOGIN_USERNAME;
            }
        } else {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!BCrypt.checkpw(reqVO.getPassword(), user.getPassword())) {
            createLoginLog(user.getId(), account, loginTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            createLoginLog(user.getId(), account, loginTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }

        // 创建 Token 令牌，记录登录日志
        LoginHelper.login(user, DeviceTypeEnum.PC);
        createLoginLog(user.getId(), account, loginTypeEnum, LoginResultEnum.SUCCESS);
        return StpUtil.getTokenValue();
    }

    public String login(EmailLoginReqVO reqVO) {
        String email = reqVO.getEmail();
        String code = reqVO.getCode();
        LoginUser user;
        LoginTypeEnum loginTypeEnum;
        if (StrUtil.isNotEmpty(email) && ReUtil.isMatch(RegexUtils.EMAIL, email)) {
            user = userApi.getUserInfo("", "", email).getCheckedData();
            loginTypeEnum = LoginTypeEnum.LOGIN_EMAIL_CODE;
        } else {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        String emailCode = valueOperations.get(RedisKeyConstants.EMAIL_CODE);
        if (!code.equals(emailCode)) {
            createLoginLog(user.getId(), email, loginTypeEnum, LoginResultEnum.BAD_EMAIL_CODE);
            throw exception(AUTH_LOGIN_BAD_EMAIL_CODE);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            createLoginLog(user.getId(), email, loginTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }

        // 创建 Token 令牌，记录登录日志
        LoginHelper.login(user, DeviceTypeEnum.PC);
        createLoginLog(user.getId(), email, loginTypeEnum, LoginResultEnum.SUCCESS);
        return StpUtil.getTokenValue();
    }

    public void generateCode(String account) {

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

    private void createLoginLog(Long userId, String account, LoginTypeEnum loginTypeEnum, LoginResultEnum loginResultEnum) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLoginType(loginTypeEnum.getType());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(UserTypeEnum.PC.getType());
        reqDTO.setAccount(account);
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
