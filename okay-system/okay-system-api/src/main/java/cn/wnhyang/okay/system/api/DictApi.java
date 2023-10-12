package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.framework.common.enums.ApiConstants;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.system.dto.dict.DictDataRespDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wnhyang
 * @date 2023/9/21
 **/
@FeignClient(name = ApiConstants.OKAY_SYSTEM_NAME)
public interface DictApi {

    String PREFIX = ApiConstants.OKAY_SYSTEM_PREFIX + "/dict";

    /**
     * @param dictType 字典类型
     * @param value    字典数据
     * @return 字典
     */
    @GetMapping(PREFIX + "/getDictData")
    CommonResult<DictDataRespDTO> getDictData(@RequestParam("dictType") String dictType,
                                              @RequestParam("value") String value);
}
