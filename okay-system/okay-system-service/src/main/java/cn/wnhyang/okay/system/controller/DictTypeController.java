package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.log.core.annotation.OperateLog;
import cn.wnhyang.okay.system.convert.DictTypeConvert;
import cn.wnhyang.okay.system.entity.DictTypePO;
import cn.wnhyang.okay.system.service.DictTypeService;
import cn.wnhyang.okay.system.vo.dicttype.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 字典类型
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
    @PostMapping
    @OperateLog(module = "后台-字典", name = "创建字典类型")
    @SaCheckPermission("system:dict:create")
    public CommonResult<Long> createDictType(@Valid @RequestBody DictTypeCreateVO reqVO) {
        Long dictTypeId = dictTypeService.createDictType(reqVO);
        return success(dictTypeId);
    }

    /**
     * 更新字典
     *
     * @param reqVO 字典数据
     * @return 结果
     */
    @PutMapping
    @OperateLog(module = "后台-字典", name = "更新字典类型")
    @SaCheckPermission("system:dict:update")
    public CommonResult<Boolean> updateDictType(@Valid @RequestBody DictTypeUpdateVO reqVO) {
        dictTypeService.updateDictType(reqVO);
        return success(true);
    }

    /**
     * 删除字典类型
     *
     * @param id 字典类型id
     * @return 结果
     */
    @DeleteMapping
    @OperateLog(module = "后台-字典", name = "删除字典类型")
    @SaCheckPermission("system:dict:delete")
    public CommonResult<Boolean> deleteDictType(@RequestParam("id") Long id) {
        dictTypeService.deleteDictType(id);
        return success(true);
    }

    /**
     * 分页字典类型
     *
     * @param reqVO 分页请求
     * @return 分页字典类型
     */
    @GetMapping("/page")
    @OperateLog(module = "后台-字典", name = "分页字典类型")
    @SaCheckPermission("system:dict:query")
    public CommonResult<PageResult<DictTypeRespVO>> pageDictTypes(@Valid DictTypePageVO reqVO) {
        return success(DictTypeConvert.INSTANCE.convertPage(dictTypeService.getDictTypePage(reqVO)));
    }

    /**
     * 查询字典类型详情
     *
     * @param id 字典类型id
     * @return 字典详情
     */
    @GetMapping
    @OperateLog(module = "后台-字典", name = "查询字典类型详情")
    @SaCheckPermission("system:dict:query")
    public CommonResult<DictTypeRespVO> getDictType(@RequestParam("id") Long id) {
        return success(DictTypeConvert.INSTANCE.convert(dictTypeService.getDictType(id)));
    }

    /**
     * 查询简单字典类型
     *
     * @return 字典类型
     */
    @GetMapping("/simpleList")
    @OperateLog(module = "后台-字典", name = "查询简单字典类型")
    @SaCheckLogin
    public CommonResult<List<DictTypeSimpleVO>> getSimpleDictTypeList() {
        List<DictTypePO> list = dictTypeService.getDictTypeList();
        return success(DictTypeConvert.INSTANCE.convertList(list));
    }
}
