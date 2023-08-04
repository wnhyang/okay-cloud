package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.system.entity.RoleDO;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static cn.wnhyang.okay.framework.common.util.CollectionUtils.convertSet;

/**
 * 角色信息表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
public interface RoleService {

    /**
     * 获得角色列表
     *
     * @param ids 角色编号数组
     * @return 角色列表
     */
    List<RoleDO> getRoleList(Collection<Long> ids);

    /**
     * 获取角色编码
     *
     * @param ids 角色编号数组
     * @return 角色编码
     */
    default Set<String> getRoleCodeList(Set<Long> ids) {
        return convertSet(getRoleList(ids), RoleDO::getCode);
    }
}
