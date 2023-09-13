package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.operatelog.core.annotation.OperateLog;
import cn.wnhyang.okay.system.service.DictTypeService;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeCreateReqVO;
import cn.wnhyang.okay.system.vo.dicttype.DictTypeUpdateReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 字典类型表
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@RestController
@RequestMapping("/system/dictType")
@RequiredArgsConstructor
public class DictTypeController {

    private final DictTypeService dictTypeService;

    /**
     * 新增字典类型
     *
     * @param reqVO 字典类型
     * @return 字典id
     */
    @PostMapping("/create")
    @OperateLog(module = "后台-字典", name = "创建字典类型")
    @SaCheckPermission("system:dict:create")
    public CommonResult<Long> createDictType(@Valid @RequestBody DictTypeCreateReqVO reqVO) {
        Long dictTypeId = dictTypeService.createDictType(reqVO);
        return success(dictTypeId);
    }

    /**
     * 更新字典
     *
     * @param reqVO 字典数据
     * @return 结果
     */
    @PutMapping("/update")
    @OperateLog(module = "后台-字典", name = "更新字典数据")
    @SaCheckPermission("system:dict:update")
    public CommonResult<Boolean> updateDictType(@Valid @RequestBody DictTypeUpdateReqVO reqVO) {
        dictTypeService.updateDictType(reqVO);
        return success(true);
    }

    /**
     * 删除字典类型
     *
     * @param id 字典类型id
     * @return 结果
     */
    @DeleteMapping("/delete")
    @OperateLog(module = "后台-字典", name = "删除字典数据")
    @SaCheckPermission("system:dict:delete")
    public CommonResult<Boolean> deleteDictType(Long id) {
        dictTypeService.deleteDictType(id);
        return success(true);
    }
}
