package cn.wnhyang.okay.system.mapper;

import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.system.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Mapper
public interface UserMapper extends BaseMapperX<UserDO> {

    default UserDO selectByUsername(String username) {
        return selectOne(UserDO::getUsername, username);
    }
}
