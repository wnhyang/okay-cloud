package cn.wnhyang.okay.framework.satoken.utils;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.wnhyang.okay.framework.common.enums.DeviceTypeEnum;
import cn.wnhyang.okay.framework.common.enums.UserConstants;
import cn.wnhyang.okay.system.dto.LoginUser;
import lombok.experimental.UtilityClass;

/**
 * @author wnhyang
 * @date 2023/7/26
 **/
@UtilityClass
public class LoginHelper {

    public static final String LOGIN_USER_KEY = "login_user";
    public static final String USER_KEY = "user_id";

    public static void login(LoginUser loginUser) {
        login(loginUser, null);
    }

    public static void login(LoginUser loginUser, DeviceTypeEnum deviceEnum) {
        SaStorage storage = SaHolder.getStorage();
        storage.set(LOGIN_USER_KEY, loginUser);
        storage.set(USER_KEY, loginUser.getId());
        SaLoginModel model = new SaLoginModel();
        if (ObjectUtil.isNotNull(deviceEnum)) {
            model.setDevice(deviceEnum.getDevice());
        }
        // 自定义分配 不同用户体系 不同 token 授权时间 不设置默认走全局 yml 配置
        // 例如: 后台用户30分钟过期 app用户1天过期
        // UserType userType = UserType.getUserType(loginUser.getUserType());
        // if (userType == UserType.SYS_USER) {
        //  model.setTimeout(86400).setActiveTimeout(1800);
        // } else if (userType == UserType.APP_USER) {
        //  model.setTimeout(86400).setActiveTimeout(1800);
        // }
        StpUtil.login(loginUser.getId(), model);
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, loginUser);
    }

    public static LoginUser getLoginUser() {
        LoginUser loginUser = (LoginUser) SaHolder.getStorage().get(LOGIN_USER_KEY);
        if (loginUser != null) {
            return loginUser;
        }
        if (!StpUtil.isLogin()) {
            return null;
        }
        SaSession session = StpUtil.getTokenSession();
        if (ObjectUtil.isNull(session)) {
            return null;
        }
        loginUser = (LoginUser) session.get(LOGIN_USER_KEY);
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        return loginUser;
    }

    public static boolean isAdministrator(Long userId) {
        return UserConstants.ADMINISTRATOR_ID.equals(userId);
    }
}
