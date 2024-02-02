package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.framework.common.enums.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wnhyang
 * @date 2024/2/2
 **/
@FeignClient(name = ApiConstants.OKAY_SYSTEM_NAME)
public interface LoginApi {
}
