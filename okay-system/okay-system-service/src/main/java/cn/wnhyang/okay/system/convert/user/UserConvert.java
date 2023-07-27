package cn.wnhyang.okay.system.convert.user;

import cn.wnhyang.okay.system.dto.LoginUser;
import cn.wnhyang.okay.system.entity.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/7/26
 **/
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    LoginUser convert(UserDO userDO);
}
