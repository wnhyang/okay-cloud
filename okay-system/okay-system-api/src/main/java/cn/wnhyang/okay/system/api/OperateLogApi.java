package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.framework.common.enums.ApiConstants;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.system.dto.OperateLogCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author wnhyang
 * @date 2023/5/31
 **/
@FeignClient(name = ApiConstants.OKAY_SYSTEM_NAME)
public interface OperateLogApi {

    String PREFIX = ApiConstants.OKAY_SYSTEM_PREFIX + "/operateLog";

    /**
     * 记录操作日志
     *
     * @param createReqDTO 操作日志请求
     * @return true/false
     */
    @PostMapping(PREFIX + "/create")
    CommonResult<Boolean> createOperateLog(@Valid @RequestBody OperateLogCreateDTO createReqDTO);

}
