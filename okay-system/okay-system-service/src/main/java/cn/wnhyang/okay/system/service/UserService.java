package cn.wnhyang.okay.system.service;

import cn.hutool.core.collection.CollUtil;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.common.util.CollectionUtils;
import cn.wnhyang.okay.system.dto.LoginUser;
import cn.wnhyang.okay.system.dto.user.UserCreateReqDTO;
import cn.wnhyang.okay.system.entity.UserDO;
import cn.wnhyang.okay.system.vo.user.UserCreateReqVO;
import cn.wnhyang.okay.system.vo.user.UserPageReqVO;
import cn.wnhyang.okay.system.vo.user.UserUpdatePasswordReqVO;
import cn.wnhyang.okay.system.vo.user.UserUpdateReqVO;

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
    UserDO getUserByUsername(String username);

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
    void registerUser(UserCreateReqDTO reqDTO);

    /**
     * 创建用户
     *
     * @param reqVO 用户信息
     * @return id
     */
    Long createUser(UserCreateReqVO reqVO);

    /**
     * 更新用户信息
     *
     * @param reqVO 用户信息
     */
    void updateUser(UserUpdateReqVO reqVO);

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
    void updateUserPassword(UserUpdatePasswordReqVO reqVO);

    /**
     * 更新用户状态
     *
     * @param id     id
     * @param status 状态
     */
    void updateUserStatus(Long id, Integer status);

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
    UserDO getUserById(Long id);

    /**
     * 查询用户信息列表
     *
     * @param reqVO 请求数据
     * @return 用户列表
     */
    PageResult<UserDO> getUserPage(UserPageReqVO reqVO);

    /**
     * 根据用户昵称查询用户列表
     *
     * @param nickname 用户昵称
     * @return 用户列表
     */
    List<UserDO> getUserListByNickname(String nickname);

    /**
     * 根据用户id查询用户map
     *
     * @param ids 用户id
     * @return 用户map
     */
    default Map<Long, UserDO> getUserMap(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return new HashMap<>();
        }
        return CollectionUtils.convertMap(getUserList(ids), UserDO::getId);
    }

    /**
     * 获取用户列表
     *
     * @param ids 用户id
     * @return 用户列表
     */
    List<UserDO> getUserList(Collection<Long> ids);
}
