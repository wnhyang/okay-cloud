package cn.wnhyang.okay.system.convert;


import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.entity.RsaPO;
import cn.wnhyang.okay.system.vo.rsa.RsaCreateVO;
import cn.wnhyang.okay.system.vo.rsa.RsaRespVO;
import cn.wnhyang.okay.system.vo.rsa.RsaUpdateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/10/10
 **/
@Mapper
public interface RsaConvert {
    RsaConvert INSTANCE = Mappers.getMapper(RsaConvert.class);

    RsaPO convert(RsaCreateVO reqVO);

    RsaPO convert(RsaUpdateVO reqVO);

    PageResult<RsaRespVO> convertPage(PageResult<RsaPO> page);

    RsaRespVO convert(RsaPO rsa);
}
