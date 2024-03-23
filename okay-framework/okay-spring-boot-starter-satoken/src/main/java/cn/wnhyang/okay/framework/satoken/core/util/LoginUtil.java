package cn.wnhyang.okay.framework.satoken.core.util;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.wnhyang.okay.framework.common.core.Login;
import cn.wnhyang.okay.framework.common.enums.DeviceType;
import cn.wnhyang.okay.framework.common.enums.UserConstants;
import cn.wnhyang.okay.framework.common.enums.UserType;
import lombok.experimental.UtilityClass;

import static cn.wnhyang.okay.framework.common.exception.GlobalErrorCode.UNAUTHORIZED;
import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * @author wnhyang
 * @date 2024/3/23
 **/
@UtilityClass
public class LoginUtil {
    public static final String LOGIN_USER_KEY = "login_user";
    public static final String USER_KEY = "user_id";

    /**
     * 获取当前用户ID
     *
     * @return 用户ID
     */
    public Long getUserId() {
        Login loginUser = getLoginUser();
        if (loginUser == null) {
            throw exception(UNAUTHORIZED);
        }
        return loginUser.getId();
    }

    /**
     * 判断是否是管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public boolean isAdministrator(Long userId) {
        return UserConstants.ADMINISTRATOR_ID.equals(userId);
    }

    public void login(Login loginUser) {
        login(loginUser, null);
    }

    public void login(Login loginUser, DeviceType deviceEnum) {
        SaLoginModel model = new SaLoginModel();
        if (ObjectUtil.isNotNull(deviceEnum)) {
            model.setDevice(deviceEnum.getDevice());
        }
        // 自定义分配 不同用户体系 不同 token 授权时间 不设置默认走全局 yml 配置
        // 例如: 后台用户30分钟过期 app用户1天过期
        UserType userType = UserType.valueOf(loginUser.getType());
        if (userType == UserType.PC) {
            model.setTimeout(1800).setActiveTimeout(600);
        } else if (userType == UserType.APP) {
            model.setTimeout(86400).setActiveTimeout(1800);
        }
        StpUtil.login(loginUser.getId(), model);
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, loginUser);
    }

    @SuppressWarnings("unckecked")
    public <T extends Login> T getLoginUser() {
        if (!StpUtil.isLogin()) {
            return null;
        }
        SaSession session = StpUtil.getTokenSession();
        if (ObjectUtil.isNull(session)) {
            return null;
        }
        T loginUser = (T) session.get(LOGIN_USER_KEY);
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        return loginUser;
    }
}
