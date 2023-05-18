package cn.wnhyang.okay.system.mapper;

import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.system.entity.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Mapper
public interface UserRoleMapper extends BaseMapperX<UserRoleDO> {

}
