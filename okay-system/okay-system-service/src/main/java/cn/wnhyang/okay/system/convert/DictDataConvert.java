package cn.wnhyang.okay.system.convert;


import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.dto.DictDataDTO;
import cn.wnhyang.okay.system.entity.DictDataPO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataCreateVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataRespVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataSimpleVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataUpdateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wnhyang
 * @date 2023/9/14
 **/
@Mapper
public interface DictDataConvert {

    DictDataConvert INSTANCE = Mappers.getMapper(DictDataConvert.class);

    DictDataPO convert(DictDataCreateVO reqVO);

    DictDataRespVO convert(DictDataPO bean);

    DictDataPO convert(DictDataUpdateVO reqVO);

    List<DictDataSimpleVO> convertList(List<DictDataPO> list);

    PageResult<DictDataRespVO> convertPage(PageResult<DictDataPO> page);

    DictDataDTO convert02(DictDataPO dictData);
}
