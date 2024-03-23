package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.system.dto.OperateLogCreateDTO;
import cn.wnhyang.okay.system.service.OperateLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 操作日志api
 *
 * @author wnhyang
 * @date 2023/6/5
 **/
@RestController
@Validated
@RequiredArgsConstructor
public class OperateLogApiImpl implements OperateLogApi {

    private final OperateLogService operateLogService;

    /**
     * 记录操作日志
     *
     * @param createReqDTO 操作日志请求
     * @return true/false
     */
    @Override
    @Async
    public CommonResult<Boolean> createOperateLog(OperateLogCreateDTO createReqDTO) {
        operateLogService.createOperateLog(createReqDTO);
        return success(true);
    }
}
