package cn.wnhyang.okay.system.mapper;

import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.system.entity.RoleResourceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色和资源关联表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Mapper
public interface RoleResourceMapper extends BaseMapperX<RoleResourceDO> {

}
