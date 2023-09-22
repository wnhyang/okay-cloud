package cn.wnhyang.okay.system.convert.dictdata;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.dto.dict.DictDataRespDTO;
import cn.wnhyang.okay.system.entity.DictDataDO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataCreateReqVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataRespVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataSimpleRespVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataUpdateReqVO;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wnhyang
 * @date 2023/9/14
 **/
public interface DictDataConvert {

    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    DictDataDO convert(DictDataCreateReqVO reqVO);

    DictDataRespVO convert(DictDataDO bean);

    DictDataDO convert(DictDataUpdateReqVO reqVO);

    List<DictDataSimpleRespVO> convertList(List<DictDataDO> list);

    PageResult<DictDataRespVO> convertPage(PageResult<DictDataDO> page);

    DictDataRespDTO convert02(DictDataDO dictData);
}
