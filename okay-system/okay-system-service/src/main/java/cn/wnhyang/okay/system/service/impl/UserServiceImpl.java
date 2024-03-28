package cn.wnhyang.okay.system.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.wnhyang.okay.framework.common.enums.UserConstants;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.common.util.CollectionUtils;
import cn.wnhyang.okay.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.wnhyang.okay.framework.satoken.core.util.LoginUtil;
import cn.wnhyang.okay.system.convert.UserConvert;
import cn.wnhyang.okay.system.dto.RoleSimpleVO;
import cn.wnhyang.okay.system.dto.UserCreateDTO;
import cn.wnhyang.okay.system.entity.RolePO;
import cn.wnhyang.okay.system.entity.UserPO;
import cn.wnhyang.okay.system.entity.UserRolePO;
import cn.wnhyang.okay.system.login.LoginUser;
import cn.wnhyang.okay.system.mapper.RoleMapper;
import cn.wnhyang.okay.system.mapper.UserMapper;
import cn.wnhyang.okay.system.mapper.UserRoleMapper;
import cn.wnhyang.okay.system.service.PermissionService;
import cn.wnhyang.okay.system.service.UserService;
import cn.wnhyang.okay.system.vo.user.UserCreateVO;
import cn.wnhyang.okay.system.vo.user.UserPageVO;
import cn.wnhyang.okay.system.vo.user.UserUpdatePasswordVO;
import cn.wnhyang.okay.system.vo.user.UserUpdateVO;
import cn.wnhyang.okay.system.vo.userprofile.UserProfileUpdatePasswordVO;
import cn.wnhyang.okay.system.vo.userprofile.UserProfileUpdateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.system.enums.ErrorCodes.*;


/**
 * 用户
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PermissionService permissionService;

    private final UserMapper userMapper;

    private final UserRoleMapper userRoleMapper;

    private final RoleMapper roleMapper;

    @Override
    public UserPO getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserLogin(Long userId, String loginIp) {
        userMapper.updateById(new UserPO().setId(userId).setLoginIp(loginIp).setLoginDate(LocalDateTime.now()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(UserCreateDTO reqDTO) {
        validateUserForCreateOrUpdate(null, reqDTO.getUsername(), reqDTO.getMobile(), reqDTO.getEmail());
        userMapper.insert(UserConvert.INSTANCE.convert(reqDTO));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createUser(UserCreateVO reqVO) {
        validateUserForCreateOrUpdate(null, reqVO.getUsername(), reqVO.getMobile(), reqVO.getEmail());
        UserPO user = UserConvert.INSTANCE.convert(reqVO);
        // TODO，新建用户，初始密码默认或者通过邮箱发送
        user.setPassword(BCrypt.hashpw(UserConstants.DEFAULT_PASSWORD));
        userMapper.insert(user);
        if (CollectionUtil.isNotEmpty(reqVO.getRoleIds())) {
            userRoleMapper.insertBatch(CollectionUtils.convertList(reqVO.getRoleIds(),
                    roleId -> new UserRolePO().setUserId(user.getId()).setRoleId(roleId)));
        }
        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserUpdateVO reqVO) {
        validateUserForCreateOrUpdate(reqVO.getId(), reqVO.getUsername(), reqVO.getMobile(), reqVO.getEmail());
        UserPO user = UserConvert.INSTANCE.convert(reqVO);
        userRoleMapper.deleteByUserId(user.getId());
        if (CollectionUtil.isNotEmpty(reqVO.getRoleIds())) {
            userRoleMapper.insertBatch(CollectionUtils.convertList(reqVO.getRoleIds(),
                    roleId -> new UserRolePO().setUserId(user.getId()).setRoleId(roleId)));
        }
        userMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long id) {
        validateUserExists(id);
        userMapper.deleteById(id);
        userRoleMapper.deleteByUserId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserPassword(UserUpdatePasswordVO reqVO) {
        validateUserExists(reqVO.getId());
        UserPO user = new UserPO().setId(reqVO.getId()).setPassword(reqVO.getPassword());
        userMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserStatus(Long id, Boolean status) {
        validateUserExists(id);
        UserPO user = new UserPO().setId(id).setStatus(status);
        userMapper.updateById(user);
    }

    @Override
    public LoginUser getLoginUser(String username, String mobile, String email) {
        LambdaQueryWrapperX<UserPO> wrapperX = new LambdaQueryWrapperX<>();
        wrapperX.eqIfPresent(UserPO::getUsername, username);
        if (StrUtil.isNotEmpty(mobile)) {
            wrapperX.or().eq(UserPO::getMobile, mobile);
        }
        if (StrUtil.isNotEmpty(email)) {
            wrapperX.or().eq(UserPO::getEmail, email);
        }
        UserPO user = userMapper.selectOne(wrapperX);
        if (user == null) {
            throw exception(USER_BAD_CREDENTIALS);
        }
        return buildLoginUser(user);
    }

    @Override
    public UserPO getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageResult<UserPO> getUserPage(UserPageVO reqVO) {
        return userMapper.selectPage(reqVO);
    }

    @Override
    public List<UserPO> getUserListByNickname(String nickname) {
        return userMapper.selectListByNickname(nickname);
    }

    @Override
    public List<UserPO> getUserList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return userMapper.selectBatchIds(ids);
    }

    @Override
    public void updateUserPassword(Long id, UserProfileUpdatePasswordVO reqVO) {
        // 校验旧密码密码
        validateOldPassword(id, reqVO.getOldPassword());
        // 执行更新
        UserPO updateObj = new UserPO().setId(id);
        // 加密密码
        updateObj.setPassword(BCrypt.hashpw(reqVO.getNewPassword()));
        userMapper.updateById(updateObj);
    }

    @Override
    public void updateUserProfile(Long id, UserProfileUpdateVO reqVO) {
        // 校验正确性
        validateUserExists(id);
        validateEmailUnique(id, reqVO.getEmail());
        validateMobileUnique(id, reqVO.getMobile());
        // 执行更新
        userMapper.updateById(UserConvert.INSTANCE.convert(reqVO).setId(id));
    }

    /**
     * 校验旧密码
     *
     * @param id          用户 id
     * @param oldPassword 旧密码
     */
    void validateOldPassword(Long id, String oldPassword) {
        UserPO user = userMapper.selectById(id);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }
        if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
            throw exception(USER_PASSWORD_FAILED);
        }
    }

    private LoginUser buildLoginUser(UserPO user) {
        LoginUser loginUser = UserConvert.INSTANCE.convert(user);
        Set<Long> roleIds = permissionService.getRoleIdListByUserId(loginUser.getId());
        List<RolePO> roleList = roleMapper.selectBatchIds(roleIds);
        List<RoleSimpleVO> roleSimpleList = roleList.stream().map(item -> {
            RoleSimpleVO respVO = new RoleSimpleVO();
            respVO.setId(item.getId()).setName(item.getName()).setValue(item.getValue());
            return respVO;
        }).collect(Collectors.toList());
        loginUser.setRoles(roleSimpleList);
        Set<String> perms = new HashSet<>();
        if (LoginUtil.isAdministrator(loginUser.getId())) {
            perms.add("*:*:*");
        } else {
            perms.addAll(permissionService.getPermissionsByRoleIds(roleIds));
        }
        loginUser.setPermissions(perms);
        return loginUser;
    }


    private void validateUserForCreateOrUpdate(Long id, String username, String mobile, String email) {
        // 校验用户存在
        validateUserExists(id);
        // 校验用户名唯一
        validateUsernameUnique(id, username);
        // 校验手机号唯一
        validateMobileUnique(id, mobile);
        // 校验邮箱唯一
        validateEmailUnique(id, email);
    }

    private void validateUserExists(Long id) {
        if (id == null) {
            return;
        }
        UserPO user = userMapper.selectById(id);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }
    }

    private void validateUsernameUnique(Long id, String username) {
        if (StrUtil.isBlank(username)) {
            return;
        }
        UserPO user = userMapper.selectByUsername(username);
        if (user == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw exception(USER_USERNAME_EXISTS);
        }
        if (!user.getId().equals(id)) {
            throw exception(USER_USERNAME_EXISTS);
        }
    }

    private void validateMobileUnique(Long id, String mobile) {
        if (StrUtil.isBlank(mobile)) {
            return;
        }
        UserPO user = userMapper.selectByMobile(mobile);
        if (user == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw exception(USER_MOBILE_EXISTS);
        }
        if (!user.getId().equals(id)) {
            throw exception(USER_MOBILE_EXISTS);
        }
    }

    private void validateEmailUnique(Long id, String email) {
        if (StrUtil.isBlank(email)) {
            return;
        }
        UserPO user = userMapper.selectByEmail(email);
        if (user == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的用户
        if (id == null) {
            throw exception(USER_EMAIL_EXISTS);
        }
        if (!user.getId().equals(id)) {
            throw exception(USER_EMAIL_EXISTS);
        }
    }

}
