package cn.wnhyang.okay.system.mapper;

import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.system.entity.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户和角色关联表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Mapper
public interface UserRoleMapper extends BaseMapperX<UserRoleDO> {

}
