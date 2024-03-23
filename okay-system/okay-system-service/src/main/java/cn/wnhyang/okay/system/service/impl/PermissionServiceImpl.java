package cn.wnhyang.okay.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.wnhyang.okay.framework.common.enums.UserConstants;
import cn.wnhyang.okay.framework.common.util.CollectionUtils;
import cn.wnhyang.okay.system.entity.MenuPO;
import cn.wnhyang.okay.system.entity.RoleMenuPO;
import cn.wnhyang.okay.system.entity.UserRolePO;
import cn.wnhyang.okay.system.mapper.MenuMapper;
import cn.wnhyang.okay.system.mapper.RoleMenuMapper;
import cn.wnhyang.okay.system.mapper.UserRoleMapper;
import cn.wnhyang.okay.system.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static cn.wnhyang.okay.framework.common.util.CollectionUtils.convertSet;

/**
 * 权限
 *
 * @author wnhyang
 * @date 2023/8/4
 **/
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final MenuMapper menuMapper;

    private final UserRoleMapper userRoleMapper;

    private final RoleMenuMapper roleMenuMapper;

    @Override
    public Set<Long> getUserRoleIdListByUserId(Long userId) {
        return convertSet(userRoleMapper.selectListByUserId(userId), UserRolePO::getRoleId);
    }

    @Override
    public Set<Long> getRoleMenuListByRoleId(Collection<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return Collections.emptySet();
        }
        // 如果是管理员的情况下，获取全部菜单编号
        if (hasAnyAdministrator(roleIds)) {
            return convertSet(menuMapper.selectList(), MenuPO::getId);
        }
        // 如果是非管理员的情况下，获得拥有的菜单编号
        return convertSet(roleMenuMapper.selectListByRoleId(roleIds), RoleMenuPO::getMenuId);
    }

    @Override
    public Set<String> getPermissionsByRoleIds(Collection<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return Collections.emptySet();
        }
        // 如果是管理员的情况下，获取全部菜单编号
        if (hasAnyAdministrator(roleIds)) {
            return Collections.singleton("*:*:*");
        }
        // 如果是非管理员的情况下，获得拥有的菜单编号
        Set<Long> menuIds = getRoleMenuListByRoleId(roleIds);
        return convertSet(menuMapper.selectBatchIds(menuIds), MenuPO::getPermission);
    }

    private boolean hasAnyAdministrator(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return false;
        }
        return ids.stream().anyMatch(UserConstants.ADMINISTRATOR_ROLE_ID::equals);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleById(Long roleId) {
        roleMenuMapper.deleteByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void roleMenu(Long roleId, Set<Long> menuIds) {
        // 获得角色拥有菜单编号
        Set<Long> dbMenuIds = convertSet(roleMenuMapper.selectListByRoleId(roleId), RoleMenuPO::getMenuId);
        // 计算新增和删除的菜单编号
        Collection<Long> createMenuIds = CollUtil.subtract(menuIds, dbMenuIds);
        Collection<Long> deleteMenuIds = CollUtil.subtract(dbMenuIds, menuIds);
        // 执行新增和删除。对于已经授权的菜单，不用做任何处理
        if (CollUtil.isNotEmpty(createMenuIds)) {
            roleMenuMapper.insertBatch(CollectionUtils.convertList(createMenuIds, menuId -> {
                RoleMenuPO entity = new RoleMenuPO();
                entity.setRoleId(roleId);
                entity.setMenuId(menuId);
                return entity;
            }));
        }
        if (CollUtil.isNotEmpty(deleteMenuIds)) {
            roleMenuMapper.deleteListByRoleIdAndMenuIds(roleId, deleteMenuIds);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userRole(Long userId, Set<Long> roleIds) {
        // 获得角色拥有角色编号
        Set<Long> dbRoleIds = convertSet(userRoleMapper.selectListByUserId(userId),
                UserRolePO::getRoleId);
        // 计算新增和删除的角色编号
        Collection<Long> createRoleIds = CollUtil.subtract(roleIds, dbRoleIds);
        Collection<Long> deleteMenuIds = CollUtil.subtract(dbRoleIds, roleIds);
        // 执行新增和删除。对于已经授权的角色，不用做任何处理
        if (!CollectionUtil.isEmpty(createRoleIds)) {
            userRoleMapper.insertBatch(CollectionUtils.convertList(createRoleIds, roleId -> {
                UserRolePO entity = new UserRolePO();
                entity.setUserId(userId);
                entity.setRoleId(roleId);
                return entity;
            }));
        }
        if (!CollectionUtil.isEmpty(deleteMenuIds)) {
            userRoleMapper.deleteListByUserIdAndRoleIdIds(userId, deleteMenuIds);
        }
    }

}
