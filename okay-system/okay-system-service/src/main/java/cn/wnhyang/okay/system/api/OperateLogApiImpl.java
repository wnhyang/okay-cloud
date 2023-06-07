package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.system.dto.OperateLogCreateReqDTO;
import cn.wnhyang.okay.system.service.OperateLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * @author wnhyang
 * @date 2023/6/5
 **/
@RestController
@Validated
@RequiredArgsConstructor
public class OperateLogApiImpl implements OperateLogApi {

    private final OperateLogService operateLogService;

    @Override
    public CommonResult<Boolean> createOperateLog(OperateLogCreateReqDTO createReqDTO) {
        operateLogService.createOperateLog(createReqDTO);
        return success(true);
    }
}
