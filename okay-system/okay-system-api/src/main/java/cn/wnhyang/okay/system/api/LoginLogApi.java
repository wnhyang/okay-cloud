package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.system.dto.loginlog.LoginLogCreateReqDTO;
import cn.wnhyang.okay.system.enums.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author wnhyang
 * @date 2023/7/26
 **/
@FeignClient(name = ApiConstants.NAME)
public interface LoginLogApi {

    String PREFIX = ApiConstants.PREFIX + "/login-log";

    /**
     * 创建登录日志
     *
     * @param reqDTO 日志信息
     */
    @PostMapping(PREFIX + "/create")
    void createLoginLog(@Valid @RequestBody LoginLogCreateReqDTO reqDTO);
}
