package cn.wnhyang.okay.system.convert.secretkey;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/10/10
 **/
@Mapper
public interface SecretKeyConvert {
    SecretKeyConvert INSTANCE = Mappers.getMapper(SecretKeyConvert.class);
}
