package cn.wnhyang.okay.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.wnhyang.okay.framework.common.enums.CommonStatusEnum;
import cn.wnhyang.okay.framework.common.enums.UserConstants;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.convert.role.RoleConvert;
import cn.wnhyang.okay.system.entity.RoleDO;
import cn.wnhyang.okay.system.mapper.RoleMapper;
import cn.wnhyang.okay.system.mapper.UserRoleMapper;
import cn.wnhyang.okay.system.redis.RedisKeyConstants;
import cn.wnhyang.okay.system.service.PermissionService;
import cn.wnhyang.okay.system.service.RoleService;
import cn.wnhyang.okay.system.vo.role.RoleCreateReqVO;
import cn.wnhyang.okay.system.vo.role.RolePageReqVO;
import cn.wnhyang.okay.system.vo.role.RoleUpdateReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.system.enums.ErrorCodeConstants.*;

/**
 * 角色信息表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    private final UserRoleMapper userRoleMapper;

    private final PermissionService permissionService;

    @Override
    public List<RoleDO> getRoleList(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return roleMapper.selectBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createRole(RoleCreateReqVO reqVO) {
        validateRoleForCreateOrUpdate(null, reqVO.getName(), reqVO.getCode());
        RoleDO role = RoleConvert.INSTANCE.convert(reqVO);
        role.setStatus(CommonStatusEnum.ENABLE.getStatus());
        roleMapper.insert(role);
        return role.getId();
    }

    @Override
    @CacheEvict(value = RedisKeyConstants.ROLE, key = "#reqVO.id")
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleUpdateReqVO reqVO) {
        // 校验是否可以更新
        validateRoleForUpdate(reqVO.getId());
        // 校验角色的唯一字段是否重复
        validateRoleForCreateOrUpdate(reqVO.getId(), reqVO.getName(), reqVO.getCode());

        // 更新到数据库
        RoleDO role = RoleConvert.INSTANCE.convert(reqVO);
        roleMapper.updateById(role);
    }

    @Override
    @CacheEvict(value = RedisKeyConstants.ROLE, key = "#id")
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleStatus(Long id, Integer status) {
        // 校验是否可以更新
        validateRoleForUpdate(id);

        // 更新状态
        RoleDO role = new RoleDO().setId(id).setStatus(status);
        roleMapper.updateById(role);
    }

    @Override
    @CacheEvict(value = RedisKeyConstants.ROLE, key = "#id")
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long id) {
        // 校验是否可以删除
        validateRoleForDelete(id);
        // 标记删除
        roleMapper.deleteById(id);
        // 删除相关数据
        permissionService.deleteRoleById(id);
    }

    @Override
    public RoleDO getRole(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public PageResult<RoleDO> getRolePage(RolePageReqVO reqVO) {
        return roleMapper.selectPage(reqVO);
    }

    private void validateRoleForDelete(Long id) {
        validateRoleForUpdate(id);
        Long count = userRoleMapper.selectCountByRoleId(id);
        if (count > 0) {
            throw exception(ROLE_HAS_USER);
        }
    }

    private void validateRoleForUpdate(Long id) {
        RoleDO roleDO = roleMapper.selectById(id);
        if (roleDO == null) {
            throw exception(ROLE_NOT_EXISTS);
        }
        // 内置角色，不允许删除
        if (UserConstants.ADMINISTRATOR_CODE.equals(roleDO.getCode())) {
            throw exception(ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE);
        }
    }

    private void validateRoleForCreateOrUpdate(Long id, String name, String code) {
        // 0. 超级管理员，不允许创建
        if (UserConstants.ADMINISTRATOR_CODE.equals(code)) {
            throw exception(ROLE_ADMIN_CODE_ERROR, code);
        }
        // 1. 该 name 名字被其它角色所使用
        RoleDO role = roleMapper.selectByName(name);
        if (role != null && !role.getId().equals(id)) {
            throw exception(ROLE_NAME_DUPLICATE, name);
        }
        // 2. 是否存在相同编码的角色
        if (!StringUtils.hasText(code)) {
            return;
        }
        // 该 code 编码被其它角色所使用
        role = roleMapper.selectByCode(code);
        if (role != null && !role.getId().equals(id)) {
            throw exception(ROLE_CODE_DUPLICATE, code);
        }
    }
}
