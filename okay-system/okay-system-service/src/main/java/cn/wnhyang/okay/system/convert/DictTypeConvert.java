package cn.wnhyang.okay.system.convert;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.entity.DictTypePO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeCreateVO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeRespVO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeSimpleVO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeUpdateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wnhyang
 * @date 2023/9/13
 **/
@Mapper
public interface DictTypeConvert {

    DictTypeConvert INSTANCE = Mappers.getMapper(DictTypeConvert.class);

    DictTypePO convert(DictTypeCreateVO reqVO);

    DictTypePO convert(DictTypeUpdateVO reqVO);

    PageResult<DictTypeRespVO> convertPage(PageResult<DictTypePO> dictTypePage);

    DictTypeRespVO convert(DictTypePO dictType);

    List<DictTypeSimpleVO> convertList(List<DictTypePO> list);
}
