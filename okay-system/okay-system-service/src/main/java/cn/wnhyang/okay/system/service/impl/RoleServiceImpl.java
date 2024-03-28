package cn.wnhyang.okay.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.wnhyang.okay.framework.common.enums.UserConstants;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.common.util.CollectionUtils;
import cn.wnhyang.okay.system.convert.RoleConvert;
import cn.wnhyang.okay.system.entity.RoleMenuPO;
import cn.wnhyang.okay.system.entity.RolePO;
import cn.wnhyang.okay.system.mapper.RoleMapper;
import cn.wnhyang.okay.system.mapper.RoleMenuMapper;
import cn.wnhyang.okay.system.mapper.UserRoleMapper;
import cn.wnhyang.okay.system.redis.RedisKey;
import cn.wnhyang.okay.system.service.RoleService;
import cn.wnhyang.okay.system.vo.role.RoleCreateVO;
import cn.wnhyang.okay.system.vo.role.RolePageVO;
import cn.wnhyang.okay.system.vo.role.RoleUpdateVO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.system.enums.ErrorCodes.*;


/**
 * 角色
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    private final RoleMenuMapper roleMenuMapper;

    private final UserRoleMapper userRoleMapper;

    @Override
    public List<RolePO> getRoleList(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return roleMapper.selectBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createRole(RoleCreateVO reqVO) {
        validateRoleForCreateOrUpdate(null, reqVO.getName(), reqVO.getValue());
        RolePO role = RoleConvert.INSTANCE.convert(reqVO);
        roleMapper.insert(role);
        if (CollectionUtil.isNotEmpty(reqVO.getMenuIds())) {
            roleMenuMapper.insertBatch(CollectionUtils.convertList(reqVO.getMenuIds(),
                    menuId -> new RoleMenuPO().setRoleId(role.getId()).setMenuId(menuId)));
        }
        return role.getId();
    }

    @Override
    @CacheEvict(value = RedisKey.ROLE, key = "#reqVO.id")
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleUpdateVO reqVO) {
        // 校验是否可以更新
        validateRoleForUpdate(reqVO.getId());
        // 校验角色的唯一字段是否重复
        validateRoleForCreateOrUpdate(reqVO.getId(), reqVO.getName(), reqVO.getValue());

        // 更新到数据库
        RolePO role = RoleConvert.INSTANCE.convert(reqVO);
        roleMenuMapper.deleteByRoleId(role.getId());
        if (CollectionUtil.isNotEmpty(reqVO.getMenuIds())) {
            roleMenuMapper.insertBatch(CollectionUtils.convertList(reqVO.getMenuIds(),
                    menuId -> new RoleMenuPO().setRoleId(role.getId()).setMenuId(menuId)));
        }
        roleMapper.updateById(role);
    }

    @Override
    @CacheEvict(value = RedisKey.ROLE, key = "#id")
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleStatus(Long id, Boolean status) {
        // 校验是否可以更新
        validateRoleForUpdate(id);

        // 更新状态
        RolePO role = new RolePO().setId(id).setStatus(status);
        roleMapper.updateById(role);
    }

    @Override
    @CacheEvict(value = RedisKey.ROLE, key = "#id")
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long id) {
        // 校验是否可以删除
        // 1、存在角色
        // 2、没有用户关联角色
        validateRoleForDelete(id);
        // 标记删除
        roleMapper.deleteById(id);
        // 删除相关数据
        roleMenuMapper.deleteByRoleId(id);
    }

    @Override
    public RolePO getRole(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public PageResult<RolePO> getRolePage(RolePageVO reqVO) {
        return roleMapper.selectPage(reqVO);
    }

    @Override
    public List<RolePO> getRoleList(Boolean status) {
        if (status == null) {
            return roleMapper.selectList();
        }
        return roleMapper.selectList(RolePO::getStatus, status);
    }

    private void validateRoleForDelete(Long id) {
        validateRoleForUpdate(id);
        Long count = userRoleMapper.selectCountByRoleId(id);
        if (count > 0) {
            throw exception(ROLE_HAS_USER);
        }
    }

    private void validateRoleForUpdate(Long id) {
        RolePO roleDO = roleMapper.selectById(id);
        if (roleDO == null) {
            throw exception(ROLE_NOT_EXISTS);
        }
        // 内置角色，不允许删除
        if (UserConstants.ADMINISTRATOR_VALUE.equals(roleDO.getValue())) {
            throw exception(ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE);
        }
    }

    private void validateRoleForCreateOrUpdate(Long id, String name, String value) {
        // 0. 超级管理员，不允许创建
        if (UserConstants.ADMINISTRATOR_VALUE.equals(value)) {
            throw exception(ROLE_ADMIN_CODE_ERROR, value);
        }
        // 1. 该 name 名字被其它角色所使用
        RolePO role = roleMapper.selectByName(name);
        if (role != null && !role.getId().equals(id)) {
            throw exception(ROLE_NAME_DUPLICATE, name);
        }
        // 2. 是否存在相同编码的角色
        if (!StringUtils.hasText(value)) {
            return;
        }
        // 该 value 编码被其它角色所使用
        role = roleMapper.selectByValue(value);
        if (role != null && !role.getId().equals(id)) {
            throw exception(ROLE_CODE_DUPLICATE, value);
        }
    }
}
