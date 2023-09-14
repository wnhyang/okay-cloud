package cn.wnhyang.okay.system.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.collection.CollUtil;
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
import cn.wnhyang.okay.system.vo.user.UserCreateReqVO;
import cn.wnhyang.okay.system.vo.user.UserPageReqVO;
import cn.wnhyang.okay.system.vo.user.UserUpdatePasswordReqVO;
import cn.wnhyang.okay.system.vo.user.UserUpdateReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.system.enums.ErrorCodeConstants.*;

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
        validateUserForCreateOrUpdate(null, reqDTO.getUsername(), reqDTO.getMobile(), reqDTO.getEmail());
        userMapper.insert(UserConvert.INSTANCE.convert(reqDTO));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createUser(UserCreateReqVO reqVO) {
        validateUserForCreateOrUpdate(null, reqVO.getUsername(), reqVO.getMobile(), reqVO.getEmail());
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
        validateUserForCreateOrUpdate(reqVO.getId(), reqVO.getUsername(), reqVO.getMobile(), reqVO.getEmail());
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
        validateUserExists(id);
        userMapper.deleteById(id);
        userRoleMapper.deleteByUserId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserPassword(UserUpdatePasswordReqVO reqVO) {
        validateUserExists(reqVO.getId());
        UserDO user = new UserDO().setId(reqVO.getId()).setPassword(reqVO.getPassword());
        userMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserStatus(Long id, Integer status) {
        validateUserExists(id);
        UserDO user = new UserDO().setId(id).setStatus(status);
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
    public UserDO getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageResult<UserDO> getUserPage(UserPageReqVO reqVO) {
        return userMapper.selectPage(reqVO);
    }

    @Override
    public List<UserDO> getUserListByNickname(String nickname) {
        return userMapper.selectListByNickname(nickname);
    }

    @Override
    public List<UserDO> getUserList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return userMapper.selectBatchIds(ids);
    }

    private LoginUser buildLoginUser(UserDO user) {
        LoginUser loginUser = UserConvert.INSTANCE.convert(user);
        Set<Long> roleIds = permissionService.getUserRoleIdListByUserId(loginUser.getId());
        loginUser.setRoleIds(roleIds);
        Set<String> roleCodes = roleService.getRoleCodeList(roleIds);
        loginUser.setRoles(roleCodes);
        Set<String> perms = new HashSet<>();
        if (LoginHelper.isAdministrator(loginUser.getId())) {
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
        UserDO user = userMapper.selectById(id);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }
    }

    private void validateUsernameUnique(Long id, String username) {
        if (StrUtil.isBlank(username)) {
            return;
        }
        UserDO user = userMapper.selectByUsername(username);
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
        UserDO user = userMapper.selectByMobile(mobile);
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
        UserDO user = userMapper.selectByEmail(email);
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
