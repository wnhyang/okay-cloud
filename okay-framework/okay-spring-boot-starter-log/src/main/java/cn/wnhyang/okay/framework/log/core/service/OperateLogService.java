package cn.wnhyang.okay.framework.log.core.service;

import cn.hutool.core.bean.BeanUtil;
import cn.wnhyang.okay.framework.log.core.dto.LogCreateDTO;
import cn.wnhyang.okay.system.api.OperateLogApi;
import cn.wnhyang.okay.system.dto.OperateLogCreateDTO;
import lombok.RequiredArgsConstructor;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * @author wnhyang
 * @date 2024/3/23
 **/
@RequiredArgsConstructor
public class OperateLogService implements LogService {

    private final OperateLogApi operateLogApi;

    /**
     * @param reqDTO 日志创建请求
     */
    @Override
    public void createLog(LogCreateDTO reqDTO) {
        OperateLogCreateDTO operateLog = BeanUtil.toBean(reqDTO, OperateLogCreateDTO.class);
        operateLogApi.createOperateLog(operateLog);
        success(true);
    }
}
