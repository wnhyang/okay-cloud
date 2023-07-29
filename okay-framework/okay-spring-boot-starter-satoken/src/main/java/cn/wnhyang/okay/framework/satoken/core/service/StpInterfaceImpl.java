package cn.wnhyang.okay.framework.satoken.core.service;

import cn.dev33.satoken.stp.StpInterface;

import java.util.List;

/**
 * @author wnhyang
 * @date 2023/7/28
 **/
public class StpInterfaceImpl  implements StpInterface {
    /**
     * 返回指定账号id所拥有的权限码集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    /**
     * 返回指定账号id所拥有的角色标识集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }
}
