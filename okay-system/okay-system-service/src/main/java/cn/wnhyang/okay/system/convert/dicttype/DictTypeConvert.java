package cn.wnhyang.okay.system.convert.dicttype;

import cn.wnhyang.okay.system.entity.DictTypeDO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeCreateReqVO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/9/13
 **/
@Mapper
public interface DictTypeConvert {
    DictTypeConvert INSTANCE = Mappers.getMapper(DictTypeConvert.class);

    DictTypeDO convert(DictTypeCreateReqVO reqVO);

    DictTypeDO convert(DictTypeUpdateReqVO reqVO);
}
