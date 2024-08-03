package cn.wnhyang.okay.auth.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.wnhyang.okay.auth.redis.RedisKey;
import cn.wnhyang.okay.auth.vo.EmailLoginVO;
import cn.wnhyang.okay.auth.vo.LoginRespVO;
import cn.wnhyang.okay.auth.vo.LoginVO;
import cn.wnhyang.okay.auth.vo.RegisterVO;
import cn.wnhyang.okay.framework.common.core.Login;
import cn.wnhyang.okay.framework.common.enums.DeviceType;
import cn.wnhyang.okay.framework.common.enums.UserType;
import cn.wnhyang.okay.framework.common.util.RegexUtils;
import cn.wnhyang.okay.framework.common.util.ServletUtils;
import cn.wnhyang.okay.framework.satoken.core.util.LoginUtil;
import cn.wnhyang.okay.system.api.LoginLogApi;
import cn.wnhyang.okay.system.api.UserApi;
import cn.wnhyang.okay.system.dto.LoginLogCreateDTO;
import cn.wnhyang.okay.system.dto.UserCreateDTO;
import cn.wnhyang.okay.system.enums.login.LoginResult;
import cn.wnhyang.okay.system.enums.login.LoginType;
import cn.wnhyang.okay.system.login.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.system.enums.ErrorCodes.*;


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

    public LoginRespVO login(LoginVO reqVO) {
        String account = reqVO.getAccount();
        LoginUser user;
        LoginType loginType;
        if (StrUtil.isNotEmpty(account)) {
            if (ReUtil.isMatch(RegexUtils.MOBILE, account)) {
                user = userApi.getLoginUser(account, account, "").getCheckedData();
                loginType = LoginType.LOGIN_MOBILE;
            } else if (ReUtil.isMatch(RegexUtils.EMAIL, account)) {
                user = userApi.getLoginUser(account, "", account).getCheckedData();
                loginType = LoginType.LOGIN_EMAIL;
            } else {
                user = userApi.getLoginUser(account, "", "").getCheckedData();
                loginType = LoginType.LOGIN_USERNAME;
            }
        } else {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!BCrypt.checkpw(reqVO.getPassword(), user.getPassword())) {
            createLoginLog(user.getId(), account, loginType, LoginResult.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), Boolean.TRUE)) {
            createLoginLog(user.getId(), account, loginType, LoginResult.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }

        // 创建 Token 令牌，记录登录日志
        LoginUtil.login(user, DeviceType.PC);
        createLoginLog(user.getId(), account, loginType, LoginResult.SUCCESS);
        LoginRespVO loginRespVO = new LoginRespVO();
        loginRespVO.setUserId(user.getId());
        loginRespVO.setToken(StpUtil.getTokenValue());
        loginRespVO.setRoles(user.getRoles());
        return loginRespVO;
    }

    public LoginRespVO login(EmailLoginVO reqVO) {
        String email = reqVO.getEmail();
        String code = reqVO.getCode();
        LoginUser user;
        LoginType loginType;
        if (StrUtil.isNotEmpty(email) && ReUtil.isMatch(RegexUtils.EMAIL, email)) {
            user = userApi.getLoginUser("", "", email).getCheckedData();
            loginType = LoginType.LOGIN_EMAIL_CODE;
        } else {
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        String emailCode = valueOperations.get(RedisKey.EMAIL_CODE);
        if (!code.equals(emailCode)) {
            createLoginLog(user.getId(), email, loginType, LoginResult.BAD_EMAIL_CODE);
            throw exception(AUTH_LOGIN_BAD_EMAIL_CODE);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), Boolean.TRUE)) {
            createLoginLog(user.getId(), email, loginType, LoginResult.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }

        // 创建 Token 令牌，记录登录日志
        LoginUtil.login(user, DeviceType.PC);
        createLoginLog(user.getId(), email, loginType, LoginResult.SUCCESS);
        LoginRespVO loginRespVO = new LoginRespVO();
        loginRespVO.setUserId(user.getId());
        loginRespVO.setToken(StpUtil.getTokenValue());
        loginRespVO.setRoles(user.getRoles());
        return loginRespVO;
    }

    public void generateEmailCode(String account) {
    }

    public void logout() {
        Login loginUser = LoginUtil.getLoginUser();
        if (loginUser != null) {
            StpUtil.logout();
            createLoginLog(loginUser.getId(), loginUser.getUsername(), LoginType.LOGOUT_SELF, LoginResult.SUCCESS);
        }
    }

    public void register(RegisterVO reqVO) {
        String username = reqVO.getUsername();
        String password = reqVO.getPassword();
        Integer userType = UserType.valueOf(reqVO.getUserType()).getType();
        UserCreateDTO reqDTO = new UserCreateDTO();
        reqDTO.setUsername(username);
        reqDTO.setNickname(username);
        reqDTO.setPassword(BCrypt.hashpw(password));
        reqDTO.setType(userType);
        userApi.registerUser(reqDTO);
    }

    private void createLoginLog(Long userId, String account, LoginType loginType, LoginResult loginResult) {
        // 插入登录日志
        LoginLogCreateDTO reqDTO = new LoginLogCreateDTO();
        reqDTO.setLoginType(loginType.getType());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(UserType.PC.getType());
        reqDTO.setAccount(account);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(loginResult.getResult());
        loginLogApi.createLoginLog(reqDTO);
        // 更新最后登录时间
        if (userId != null && Objects.equals(LoginResult.SUCCESS.getResult(), loginResult.getResult())) {
            userApi.updateUserLogin(userId, ServletUtils.getClientIP());
        }
    }
}
