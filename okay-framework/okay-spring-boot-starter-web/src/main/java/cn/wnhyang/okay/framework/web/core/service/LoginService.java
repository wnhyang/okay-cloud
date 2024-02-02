package cn.wnhyang.okay.framework.web.core.service;

import cn.wnhyang.okay.framework.common.core.Login;
import cn.wnhyang.okay.framework.common.enums.DeviceTypeEnum;
import cn.wnhyang.okay.framework.common.enums.UserConstants;

import static cn.wnhyang.okay.framework.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;
import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
public interface LoginService {

    /**
     * 登录
     *
     * @param loginUser  登录信息
     * @param deviceEnum 设备类型
     */
    void login(Login loginUser, DeviceTypeEnum deviceEnum);

    /**
     * 获取当前登录用户
     *
     * @return 登录用户
     */
    <T extends Login> T getLoginUser();

    /**
     * 获取当前用户ID
     *
     * @return 用户ID
     */
    default Long getUserId() {
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
    default boolean isAdministrator(Long userId) {
        return UserConstants.ADMINISTRATOR_ID.equals(userId);
    }
}
