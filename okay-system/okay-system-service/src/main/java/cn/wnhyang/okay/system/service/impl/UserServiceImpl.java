package cn.wnhyang.okay.system.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.common.util.CollectionUtils;
import cn.wnhyang.okay.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.wnhyang.okay.framework.satoken.utils.LoginHelper;
import cn.wnhyang.okay.system.convert.user.UserConvert;
import cn.wnhyang.okay.system.dto.LoginUser;
import cn.wnhyang.okay.system.dto.user.UserCreateReqDTO;
import cn.wnhyang.okay.system.entity.UserDO;
import cn.wnhyang.okay.system.entity.UserRoleDO;
import cn.wnhyang.okay.system.mapper.UserMapper;
import cn.wnhyang.okay.system.mapper.UserRoleMapper;
import cn.wnhyang.okay.system.service.PermissionService;
import cn.wnhyang.okay.system.service.RoleService;
import cn.wnhyang.okay.system.service.UserService;
import cn.wnhyang.okay.system.vo.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.system.enums.ErrorCodeConstants.*;

/**
 * 用户信息表
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

    private final RoleService roleService;

    @Override
    public UserDO getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserLogin(Long userId, String loginIp) {
        userMapper.updateById(new UserDO().setId(userId).setLoginIp(loginIp).setLoginDate(LocalDateTime.now()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(UserCreateReqDTO reqDTO) {
        checkUserForCreateOrUpdate(reqDTO.getUsername(), reqDTO.getMobile(), reqDTO.getEmail());
        userMapper.insert(UserConvert.INSTANCE.convert(reqDTO));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createUser(UserCreateReqVO reqVO) {
        checkUserForCreateOrUpdate(reqVO.getUsername(), reqVO.getMobile(), reqVO.getEmail());
        UserDO user = UserConvert.INSTANCE.convert(reqVO);
        user.setPassword(BCrypt.hashpw(reqVO.getPassword()));
        userMapper.insert(user);
        if (CollectionUtil.isNotEmpty(reqVO.getRoleIds())) {
            userRoleMapper.insertBatch(CollectionUtils.convertList(reqVO.getRoleIds(),
                    roleId -> new UserRoleDO().setUserId(user.getId()).setRoleId(roleId)));
        }
        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserUpdateReqVO reqVO) {
        checkUserForCreateOrUpdate(reqVO.getUsername(), reqVO.getMobile(), reqVO.getEmail());
        UserDO user = UserConvert.INSTANCE.convert(reqVO);
        userRoleMapper.deleteByUserId(user.getId());
        if (CollectionUtil.isNotEmpty(reqVO.getRoleIds())) {
            userRoleMapper.insertBatch(CollectionUtils.convertList(reqVO.getRoleIds(),
                    roleId -> new UserRoleDO().setUserId(user.getId()).setRoleId(roleId)));
        }
        userMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long id) {
        checkUserExists(id);
        userMapper.deleteById(id);
        userRoleMapper.deleteByUserId(id);
    }

    @Override
    public void updateUserPassword(UserUpdatePasswordReqVO reqVO) {
        checkUserExists(reqVO.getId());
        UserDO user = new UserDO().setId(reqVO.getId()).setPassword(reqVO.getPassword());
        userMapper.updateById(user);
    }

    @Override
    public void updateUserStatus(UserUpdateStatusReqVO reqVO) {
        checkUserExists(reqVO.getId());
        UserDO user = new UserDO().setId(reqVO.getId()).setStatus(reqVO.getStatus());
        userMapper.updateById(user);
    }

    @Override
    public LoginUser getUserInfo(String username, String mobile, String email) {
        LambdaQueryWrapperX<UserDO> wrapperX = new LambdaQueryWrapperX<>();
        wrapperX.eqIfPresent(UserDO::getUsername, username);
        if (StrUtil.isNotEmpty(mobile)) {
            wrapperX.or().eq(UserDO::getMobile, mobile);
        }
        if (StrUtil.isNotEmpty(email)) {
            wrapperX.or().eq(UserDO::getEmail, email);
        }
        UserDO user = userMapper.selectOne(wrapperX);
        if (user == null) {
            throw exception(USER_BAD_CREDENTIALS);
        }
        return buildLoginUser(user);
    }

    @Override
    public UserDO getUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageResult<UserDO> getUserPage(UserPageReqVO reqVO) {
        return userMapper.selectPage(reqVO);
    }

    private LoginUser buildLoginUser(UserDO user) {
        LoginUser loginUser = UserConvert.INSTANCE.convert(user);
        Set<Long> roleIds = permissionService.getUserRoleIdListByUserId(loginUser.getId());
        loginUser.setRoleIds(roleIds);
        Set<String> roleCodes = roleService.getRoleCodeList(roleIds);
        loginUser.setRoleCode(roleCodes);
        Set<String> resourcePerms = new HashSet<>();
        if (LoginHelper.isAdministrator(loginUser.getId())) {
            resourcePerms.add("*:*:*");
        } else {
            resourcePerms.addAll(permissionService.getRoleResourcePermsByRoleId(roleIds));
        }
        loginUser.setResourcePermission(resourcePerms);
        return loginUser;
    }

    private void checkUserExists(Long id) {
        if (id == null) {
            return;
        }
        UserDO user = userMapper.selectById(id);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }
    }

    private void checkUserForCreateOrUpdate(String username, String mobile, String email) {
        if (!checkUsernameUnique(username)) {
            throw exception(USER_USERNAME_EXISTS);
        }
        if (!checkMobileUnique(mobile)) {
            throw exception(USER_MOBILE_EXISTS);
        }
        if (!checkEmailUnique(email)) {
            throw exception(USER_EMAIL_EXISTS);
        }
    }

    private boolean checkUsernameUnique(String username) {
        return userMapper.selectOne(UserDO::getUsername, username) == null;
    }

    private boolean checkMobileUnique(String mobile) {
        return userMapper.selectOne(UserDO::getMobile, mobile) == null;
    }

    private boolean checkEmailUnique(String email) {
        return userMapper.selectOne(UserDO::getEmail, email) == null;
    }

}
