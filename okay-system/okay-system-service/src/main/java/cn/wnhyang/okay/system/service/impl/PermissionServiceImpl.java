package cn.wnhyang.okay.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.wnhyang.okay.system.entity.ResourceDO;
import cn.wnhyang.okay.system.entity.RoleResourceDO;
import cn.wnhyang.okay.system.entity.UserRoleDO;
import cn.wnhyang.okay.system.mapper.ResourceMapper;
import cn.wnhyang.okay.system.mapper.RoleResourceMapper;
import cn.wnhyang.okay.system.mapper.UserRoleMapper;
import cn.wnhyang.okay.system.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static cn.wnhyang.okay.framework.common.util.CollectionUtils.convertSet;

/**
 * @author wnhyang
 * @date 2023/8/4
 **/
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final ResourceMapper resourceMapper;

    private final UserRoleMapper userRoleMapper;

    private final RoleResourceMapper roleResourceMapper;

    @Override
    public Set<Long> getUserRoleIdListByUserId(Long userId) {
        return convertSet(userRoleMapper.selectListByUserId(userId), UserRoleDO::getRoleId);
    }

    @Override
    public Set<Long> getRoleResourceListByRoleId(Collection<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return Collections.emptySet();
        }
        // 如果是非管理员的情况下，获得拥有的菜单编号
        return convertSet(roleResourceMapper.selectListByRoleId(roleIds), RoleResourceDO::getResourceId);
    }

    @Override
    public Set<String> getRoleResourcePermsByRoleId(Collection<Long> roleIds) {
        Set<Long> resourceIds = getRoleResourceListByRoleId(roleIds);
        return convertSet(resourceMapper.selectBatchIds(resourceIds), ResourceDO::getPermission);
    }

    @Override
    public void deleteRoleById(Long roleId) {
        userRoleMapper.deleteByRoleId(roleId);

        roleResourceMapper.deleteByRoleId(roleId);
    }
}
