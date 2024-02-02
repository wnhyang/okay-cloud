package cn.wnhyang.okay.framework.common.core;

import java.util.Set;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
public interface Login {

    /**
     * 获取用户ID
     *
     * @return 用户id
     */
    Long getId();

    /**
     * 获取用户账号
     *
     * @return 用户账号
     */
    String getUsername();

    /**
     * 获取用户类型
     *
     * @return 用户类型
     */
    Integer getType();

    /**
     * 获取用户角色ID
     *
     * @return 用户角色id
     */
    Set<Long> getRoleIds();

    /**
     * 获取用户角色
     *
     * @return 用户角色
     */
    Set<String> getRoleValues();

    /**
     * 获取用户权限
     *
     * @return 用户权限
     */
    Set<String> getPermissions();


}
