package cn.wnhyang.okay.system.convert;

import cn.wnhyang.okay.system.dto.OperateLogCreateReqDTO;
import cn.wnhyang.okay.system.entity.OperateLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/6/6
 **/
@Mapper
public interface OperateLogConvert {
    OperateLogConvert INSTANCE = Mappers.getMapper(OperateLogConvert.class);

    OperateLogDO convert(OperateLogCreateReqDTO reqDTO);
}
