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
    default Set<Long> getRoleIdListByUserId(Long userId) {
        return getRoleIdListByUserId(singleton(userId));
    }

    /**
     * 根据用户id获取
     *
     * @param userId 用户id
     * @return 角色集合
     */
    Set<Long> getRoleIdListByUserId(Collection<Long> userId);

    /**
     * 根据角色id获取菜单id集合
     *
     * @param roleId 角色编号
     * @return 菜单编号集合
     */
    default Set<Long> getMenuIdListByRoleId(Long roleId) {
        return getMenuIdListByRoleId(singleton(roleId));
    }

    /**
     * 根据角色id获取菜单id集合
     *
     * @param roleIds 角色编号数组
     * @return 菜单编号集合
     */
    Set<Long> getMenuIdListByRoleId(Collection<Long> roleIds);

    /**
     * 根据角色id获取权限集合
     *
     * @param roleId 角色编号
     * @return 菜单编号集合
     */
    default Set<String> getPermissionsByRoleIds(Long roleId) {
        return getPermissionsByRoleIds(singleton(roleId));
    }

    /**
     * 根据角色id获取权限集合
     *
     * @param roleIds 角色编号数组
     * @return 菜单编号集合
     */
    Set<String> getPermissionsByRoleIds(Collection<Long> roleIds);

    /**
     * 给角色-菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单id集合
     */
    void roleMenu(Long roleId, Set<Long> menuIds);

    /**
     * 给用户-角色
     *
     * @param userId  用户id
     * @param roleIds 角色id集合
     */
    void userRole(Long userId, Set<Long> roleIds);
}
