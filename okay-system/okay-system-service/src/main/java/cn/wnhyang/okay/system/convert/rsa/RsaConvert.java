package cn.wnhyang.okay.system.convert.rsa;

import cn.wnhyang.okay.system.entity.RsaDO;
import cn.wnhyang.okay.system.vo.rsa.RsaCreateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/10/10
 **/
@Mapper
public interface RsaConvert {
    RsaConvert INSTANCE = Mappers.getMapper(RsaConvert.class);

    RsaDO convert(RsaCreateReqVO reqVO);
}
