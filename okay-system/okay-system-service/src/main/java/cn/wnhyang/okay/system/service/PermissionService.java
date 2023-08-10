package cn.wnhyang.okay.system.service;

import java.util.Collection;
import java.util.Set;

import static java.util.Collections.singleton;

/**
 * @author wnhyang
 * @date 2023/8/4
 **/
public interface PermissionService {

    /**
     * 根据用户id获取
     *
     * @param userId 用户id
     * @return 角色集合
     */
    Set<Long> getUserRoleIdListByUserId(Long userId);

    /**
     * 获得角色拥有的菜单编号集合
     *
     * @param roleId 角色编号
     * @return 菜单编号集合
     */
    default Set<Long> getRoleResourceListByRoleId(Long roleId) {
        return getRoleResourceListByRoleId(singleton(roleId));
    }

    /**
     * 获得角色们拥有的菜单编号集合
     *
     * @param roleIds 角色编号数组
     * @return 菜单编号集合
     */
    Set<Long> getRoleResourceListByRoleId(Collection<Long> roleIds);

    /**
     * 获得角色拥有的菜单权限集合
     *
     * @param roleId 角色编号
     * @return 菜单编号集合
     */
    default Set<String> getRoleResourcePermsByRoleId(Long roleId) {
        return getRoleResourcePermsByRoleId(singleton(roleId));
    }

    /**
     * 获得角色们拥有的菜单权限集合
     *
     * @param roleIds 角色编号数组
     * @return 菜单编号集合
     */
    Set<String> getRoleResourcePermsByRoleId(Collection<Long> roleIds);

    /**
     * 删除角色-用户、角色-资源
     *
     * @param roleId 角色id
     */
    void deleteRoleById(Long roleId);
}
