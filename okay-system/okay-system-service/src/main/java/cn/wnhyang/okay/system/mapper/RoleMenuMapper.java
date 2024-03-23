package cn.wnhyang.okay.system.mapper;


import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.system.entity.RoleMenuPO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 角色和菜单关联表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Mapper
public interface RoleMenuMapper extends BaseMapperX<RoleMenuPO> {

    default List<RoleMenuPO> selectListByRoleId(Collection<Long> roleIds) {
        return selectList(RoleMenuPO::getRoleId, roleIds);
    }

    default void deleteByRoleId(Long roleId) {
        delete(new LambdaQueryWrapper<RoleMenuPO>().eq(RoleMenuPO::getRoleId, roleId));
    }

    default Long selectCountByMenuId(Long menuId) {
        return selectCount(RoleMenuPO::getMenuId, menuId);
    }

    default List<RoleMenuPO> selectListByRoleId(Long roleId) {
        return selectList(RoleMenuPO::getRoleId, roleId);
    }

    default void deleteListByRoleIdAndMenuIds(Long roleId, Collection<Long> menuIds) {
        delete(new LambdaQueryWrapper<RoleMenuPO>()
                .eq(RoleMenuPO::getRoleId, roleId)
                .in(RoleMenuPO::getMenuId, menuIds));
    }
}
