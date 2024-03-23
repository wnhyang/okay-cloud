package cn.wnhyang.okay.system.convert;


import cn.wnhyang.okay.framework.log.core.dto.LogCreateDTO;
import cn.wnhyang.okay.system.dto.OperateLogCreateDTO;
import cn.wnhyang.okay.system.entity.OperateLogPO;
import cn.wnhyang.okay.system.vo.operatelog.OperateLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2023/6/6
 **/
@Mapper
public interface OperateLogConvert {
    OperateLogConvert INSTANCE = Mappers.getMapper(OperateLogConvert.class);

    OperateLogPO convert(OperateLogCreateDTO reqDTO);

    OperateLogVO convert(OperateLogPO operateLog);

    OperateLogCreateDTO convert(LogCreateDTO reqDTO);
}
