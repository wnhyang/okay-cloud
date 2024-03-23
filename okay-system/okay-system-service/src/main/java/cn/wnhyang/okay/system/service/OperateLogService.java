package cn.wnhyang.okay.system.service;


import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.system.dto.OperateLogCreateDTO;
import cn.wnhyang.okay.system.entity.OperateLogPO;
import cn.wnhyang.okay.system.vo.operatelog.OperateLogPageVO;

/**
 * 操作日志记录
 *
 * @author wnhyang
 * @since 2023/06/05
 */
public interface OperateLogService {

    /**
     * 记录操作日志
     *
     * @param createReqDTO 操作日志请求
     */
    void createOperateLog(OperateLogCreateDTO createReqDTO);

    /**
     * 分页查询操作日志
     *
     * @param reqVO 分页请求
     * @return 分页操作日志
     */
    PageResult<OperateLogPO> getOperateLogPage(OperateLogPageVO reqVO);
}
