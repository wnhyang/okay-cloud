package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.system.convert.DictDataConvert;
import cn.wnhyang.okay.system.dto.DictDataDTO;
import cn.wnhyang.okay.system.entity.DictDataPO;
import cn.wnhyang.okay.system.service.DictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 字典api
 *
 * @author wnhyang
 * @date 2023/9/21
 **/
@RestController
@Validated
@RequiredArgsConstructor
public class DictApiImpl implements DictApi {

    private final DictDataService dictDataService;

    /**
     * @param dictType 字典类型
     * @param value    字典数据
     * @return 字典
     */
    @Override
    public CommonResult<DictDataDTO> getDictData(String dictType, String value) {
        DictDataPO dictData = dictDataService.getDictData(dictType, value);
        return success(DictDataConvert.INSTANCE.convert02(dictData));
    }
}
