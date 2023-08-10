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
    default Set<Long> getRoleMenuListByRoleId(Long roleId) {
        return getRoleMenuListByRoleId(singleton(roleId));
    }

    /**
     * 获得角色们拥有的菜单编号集合
     *
     * @param roleIds 角色编号数组
     * @return 菜单编号集合
     */
    Set<Long> getRoleMenuListByRoleId(Collection<Long> roleIds);

    /**
     * 获得角色拥有的菜单权限集合
     *
     * @param roleId 角色编号
     * @return 菜单编号集合
     */
    default Set<String> getRoleMenuPermsByRoleId(Long roleId) {
        return getRoleMenuPermsByRoleId(singleton(roleId));
    }

    /**
     * 获得角色们拥有的菜单权限集合
     *
     * @param roleIds 角色编号数组
     * @return 菜单编号集合
     */
    Set<String> getRoleMenuPermsByRoleId(Collection<Long> roleIds);

    /**
     * 删除角色-用户、角色-菜单
     *
     * @param roleId 角色id
     */
    void deleteRoleById(Long roleId);
}
