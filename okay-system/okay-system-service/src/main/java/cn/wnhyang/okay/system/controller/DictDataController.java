package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.operatelog.core.annotation.OperateLog;
import cn.wnhyang.okay.system.service.DictDataService;
import cn.wnhyang.okay.system.vo.dictdata.DictDataCreateReqVO;
import cn.wnhyang.okay.system.vo.dictdata.DictDataUpdateReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 字典数据
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@RestController
@RequestMapping("/system/dictData")
@RequiredArgsConstructor
public class DictDataController {

    private final DictDataService dictDataService;

    /**
     * 创建字典数据
     *
     * @param reqVO 字典数据
     * @return 字典数据id
     */
    @PostMapping("/create")
    @OperateLog(module = "后台-字典", name = "创建字典数据")
    @SaCheckPermission("system:dict:create")
    public CommonResult<Long> createDictData(@Valid @RequestBody DictDataCreateReqVO reqVO) {
        Long dictDataId = dictDataService.createDictData(reqVO);
        return success(dictDataId);
    }

    /**
     * 更新字典数据
     *
     * @param reqVO 字典数据
     * @return 结果
     */
    @PutMapping("/update")
    @OperateLog(module = "后台-字典", name = "更新字典数据")
    @SaCheckPermission("system:dict:update")
    public CommonResult<Boolean> updateDictData(@Valid @RequestBody DictDataUpdateReqVO reqVO) {
        dictDataService.updateDictData(reqVO);
        return success(true);
    }

    /**
     * 删除字典数据
     *
     * @param id 字典数据id
     * @return 结果
     */
    @DeleteMapping("/delete")
    @OperateLog(module = "后台-字典", name = "删除字典数据")
    @SaCheckPermission("system:dict:delete")
    public CommonResult<Boolean> deleteDictData(Long id) {
        dictDataService.deleteDictData(id);
        return success(true);
    }

}
