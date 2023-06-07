package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.system.enums.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author wnhyang
 * @date 2023/5/12
 **/
@FeignClient(name = ApiConstants.NAME)
public interface UserApi {
}
