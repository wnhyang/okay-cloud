package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.system.dto.operatelog.OperateLogCreateReqDTO;

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
    void createOperateLog(OperateLogCreateReqDTO createReqDTO);
}
