package cn.wnhyang.okay.system.mapper;

import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.system.entity.RoleResourceDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 角色和资源关联表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Mapper
public interface RoleResourceMapper extends BaseMapperX<RoleResourceDO> {

    default List<RoleResourceDO> selectListByRoleId(Collection<Long> roleIds) {
        return selectList(RoleResourceDO::getRoleId, roleIds);
    }

    default void deleteByRoleId(Long roleId) {
        delete(new LambdaQueryWrapper<RoleResourceDO>().eq(RoleResourceDO::getRoleId, roleId));
    }
}
