package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.system.entity.UserDO;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author wnhyang
 * @since 2023/05/14
 */
public interface UserService {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserDO getUserByUsername(String username);

    /**
     * 更新用户登录信息
     *
     * @param userId  用户id
     * @param loginIp 登录ip
     */
    void updateUserLogin(Long userId, String loginIp);

}
