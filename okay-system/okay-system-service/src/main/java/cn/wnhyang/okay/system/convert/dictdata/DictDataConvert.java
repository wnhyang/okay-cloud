package cn.wnhyang.okay.system.convert.dictdata;

import cn.wnhyang.okay.system.entity.DictDataDO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataCreateReqVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataUpdateReqVO;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/9/14
 **/
public interface DictDataConvert {

    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    DictDataDO convert(DictDataCreateReqVO reqVO);

    DictDataDO convert(DictDataUpdateReqVO reqVO);
}
