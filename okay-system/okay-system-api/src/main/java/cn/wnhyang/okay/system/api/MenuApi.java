package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.framework.common.enums.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wnhyang
 * @date 2023/6/5
 **/
@FeignClient(name = ApiConstants.OKAY_SYSTEM_NAME)
public interface MenuApi {
}
