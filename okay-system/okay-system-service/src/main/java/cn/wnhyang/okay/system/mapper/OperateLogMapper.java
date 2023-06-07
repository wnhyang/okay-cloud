package cn.wnhyang.okay.system.mapper;

import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.system.entity.OperateLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 操作日志记录 Mapper 接口
 * </p>
 *
 * @author wnhyang
 * @since 2023/06/05
 */
@Mapper
public interface OperateLogMapper extends BaseMapperX<OperateLogDO> {

}
