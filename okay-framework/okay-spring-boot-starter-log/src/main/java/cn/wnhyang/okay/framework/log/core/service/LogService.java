package cn.wnhyang.okay.framework.log.core.service;

import cn.wnhyang.okay.framework.log.core.dto.LogCreateDTO;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
public interface LogService {

    /**
     * 创建日志
     *
     * @param reqDTO 日志创建请求
     */
    void createLog(LogCreateDTO reqDTO);
}
