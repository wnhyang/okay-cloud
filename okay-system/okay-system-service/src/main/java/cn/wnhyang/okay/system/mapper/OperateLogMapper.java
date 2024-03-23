package cn.wnhyang.okay.system.mapper;

import cn.wnhyang.okay.framework.common.exception.GlobalErrorCode;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.wnhyang.okay.system.entity.OperateLogPO;
import cn.wnhyang.okay.system.vo.operatelog.OperateLogPageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

/**
 * 操作日志记录
 *
 * @author wnhyang
 * @since 2023/06/05
 */
@Mapper
public interface OperateLogMapper extends BaseMapperX<OperateLogPO> {

    default PageResult<OperateLogPO> selectPage(OperateLogPageVO reqVO, Collection<Long> userIds) {
        LambdaQueryWrapperX<OperateLogPO> query = new LambdaQueryWrapperX<OperateLogPO>()
                .likeIfPresent(OperateLogPO::getModule, reqVO.getModule())
                .inIfPresent(OperateLogPO::getUserId, userIds)
                .eqIfPresent(OperateLogPO::getType, reqVO.getType())
                .betweenIfPresent(OperateLogPO::getStartTime, reqVO.getStartTime(), reqVO.getEndTime());
        if (Boolean.TRUE.equals(reqVO.getSuccess())) {
            query.eq(OperateLogPO::getResultCode, GlobalErrorCode.SUCCESS.getCode());
        } else if (Boolean.FALSE.equals(reqVO.getSuccess())) {
            query.gt(OperateLogPO::getResultCode, GlobalErrorCode.SUCCESS.getCode());
        }
        // 降序
        query.orderByDesc(OperateLogPO::getId);
        return selectPage(reqVO, query);
    }
}
