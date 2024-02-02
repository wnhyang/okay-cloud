package cn.wnhyang.okay.framework.log.core.service;

import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.log.core.dto.LogCreateReqDTO;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
public interface LogService {

    /**
     * 创建日志
     *
     * @param reqDTO 日志创建请求
     * @return 日志创建结果
     */
    CommonResult<Boolean> createLog(LogCreateReqDTO reqDTO);
}
