package cn.wnhyang.okay.system.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.collection.CollectionUtil;
import cn.wnhyang.okay.framework.common.util.CollectionUtils;
import cn.wnhyang.okay.system.convert.user.UserConvert;
import cn.wnhyang.okay.system.dto.user.UserCreateReqDTO;
import cn.wnhyang.okay.system.entity.UserDO;
import cn.wnhyang.okay.system.entity.UserRoleDO;
import cn.wnhyang.okay.system.mapper.UserMapper;
import cn.wnhyang.okay.system.mapper.UserRoleMapper;
import cn.wnhyang.okay.system.service.UserService;
import cn.wnhyang.okay.system.vo.user.UserCreateReqVO;
import cn.wnhyang.okay.system.vo.user.UserUpdatePasswordReqVO;
import cn.wnhyang.okay.system.vo.user.UserUpdateReqVO;
import cn.wnhyang.okay.system.vo.user.UserUpdateStatusReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    private final UserMapper userMapper;

    private final UserRoleMapper userRoleMapper;

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
        if (CollectionUtil.isNotEmpty(user.getRoleIds())) {
            userRoleMapper.insertBatch(CollectionUtils.convertList(user.getRoleIds(),
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
        if (CollectionUtil.isNotEmpty(user.getRoleIds())) {
            userRoleMapper.insertBatch(CollectionUtils.convertList(user.getRoleIds(),
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
