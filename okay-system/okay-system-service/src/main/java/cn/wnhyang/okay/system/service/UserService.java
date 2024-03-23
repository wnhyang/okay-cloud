package cn.wnhyang.okay.system.service;


import cn.hutool.core.collection.CollUtil;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.common.util.CollectionUtils;
import cn.wnhyang.okay.system.dto.UserCreateDTO;
import cn.wnhyang.okay.system.entity.UserPO;
import cn.wnhyang.okay.system.login.LoginUser;
import cn.wnhyang.okay.system.vo.user.UserCreateVO;
import cn.wnhyang.okay.system.vo.user.UserPageVO;
import cn.wnhyang.okay.system.vo.user.UserUpdatePasswordVO;
import cn.wnhyang.okay.system.vo.user.UserUpdateVO;
import cn.wnhyang.okay.system.vo.userprofile.UserProfileUpdatePasswordVO;
import cn.wnhyang.okay.system.vo.userprofile.UserProfileUpdateVO;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户信息表
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
    UserPO getUserByUsername(String username);

    /**
     * 更新用户登录信息
     *
     * @param userId  用户id
     * @param loginIp 登录ip
     */
    void updateUserLogin(Long userId, String loginIp);

    /**
     * 注册用户
     *
     * @param reqDTO 用户信息
     */
    void registerUser(UserCreateDTO reqDTO);

    /**
     * 创建用户
     *
     * @param reqVO 用户信息
     * @return id
     */
    Long createUser(UserCreateVO reqVO);

    /**
     * 更新用户信息
     *
     * @param reqVO 用户信息
     */
    void updateUser(UserUpdateVO reqVO);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void deleteUser(Long id);

    /**
     * 更新用户密码
     *
     * @param reqVO id+密码
     */
    void updateUserPassword(UserUpdatePasswordVO reqVO);

    /**
     * 更新用户状态
     *
     * @param id     id
     * @param status 状态
     */
    void updateUserStatus(Long id, Boolean status);

    /**
     * 根据用户名/手机号/邮箱获取用户信息
     *
     * @param username 用户名
     * @param mobile   手机号
     * @param email    邮箱
     * @return loginUser
     */
    LoginUser getUserInfo(String username, String mobile, String email);

    /**
     * 查询用户信息
     *
     * @param id id
     * @return 用户
     */
    UserPO getUserById(Long id);

    /**
     * 查询用户信息列表
     *
     * @param reqVO 请求数据
     * @return 用户列表
     */
    PageResult<UserPO> getUserPage(UserPageVO reqVO);

    /**
     * 根据用户昵称查询用户列表
     *
     * @param nickname 用户昵称
     * @return 用户列表
     */
    List<UserPO> getUserListByNickname(String nickname);

    /**
     * 根据用户id查询用户map
     *
     * @param ids 用户id
     * @return 用户map
     */
    default Map<Long, UserPO> getUserMap(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return new HashMap<>();
        }
        return CollectionUtils.convertMap(getUserList(ids), UserPO::getId);
    }

    /**
     * 获取用户列表
     *
     * @param ids 用户id
     * @return 用户列表
     */
    List<UserPO> getUserList(Collection<Long> ids);

    /**
     * 更新用户密码
     *
     * @param id    用户id
     * @param reqVO 请求数据
     */
    void updateUserPassword(Long id, UserProfileUpdatePasswordVO reqVO);

    /**
     * 更新用户信息
     *
     * @param id    用户id
     * @param reqVO 请求数据
     */
    void updateUserProfile(Long id, UserProfileUpdateVO reqVO);
}
