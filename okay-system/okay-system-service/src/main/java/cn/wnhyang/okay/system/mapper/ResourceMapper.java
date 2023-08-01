package cn.wnhyang.okay.system.mapper;

import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.system.entity.ResourceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资源权限表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Mapper
public interface ResourceMapper extends BaseMapperX<ResourceDO> {

}
