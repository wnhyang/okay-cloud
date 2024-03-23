package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.log.core.annotation.OperateLog;
import cn.wnhyang.okay.system.convert.RsaConvert;
import cn.wnhyang.okay.system.service.RsaService;
import cn.wnhyang.okay.system.vo.rsa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * rsa密钥
 *
 * @author wnhyang
 * @since 2023/10/10
 */
@RestController
@RequestMapping("/system/rsa")
@RequiredArgsConstructor
public class RsaController {

    private final RsaService rsaService;

    /**
     * 生成密钥
     *
     * @return 密钥对
     */
    @PostMapping("/generate")
    @OperateLog(module = "后台-密钥", name = "生成密钥")
    @SaCheckPermission("system:rsa:generate")
    public CommonResult<RsaPairVO> generateKeyPair() {
        return success(rsaService.generateKeyPair());
    }

    /**
     * 创建rsa密钥
     *
     * @param reqVO 密钥，可以是单个私钥或公钥
     * @return id
     */
    @PostMapping
    @OperateLog(module = "后台-密钥", name = "创建密钥")
    @SaCheckPermission("system:rsa:create")
    public CommonResult<Long> createRsa(@Valid @RequestBody RsaCreateVO reqVO) {
        return success(rsaService.createSecretKey(reqVO));
    }

    /**
     * 更新密钥
     *
     * @param reqVO 更新密钥
     * @return true
     */
    @PutMapping
    @OperateLog(module = "后台-密钥", name = "更新密钥")
    @SaCheckPermission("system:rsa:update")
    public CommonResult<Boolean> updateRsa(@Valid @RequestBody RsaUpdateVO reqVO) {
        rsaService.updateRsa(reqVO);
        return success(true);
    }

    /**
     * 删除密钥
     *
     * @param id 密钥id
     * @return true
     */
    @DeleteMapping
    @OperateLog(module = "后台-密钥", name = "删除密钥")
    @SaCheckPermission("system:rsa:delete")
    public CommonResult<Boolean> deleteRsa(@RequestParam("id") Long id) {
        rsaService.deleteRsa(id);
        return success(true);
    }

    /**
     * 分页密钥
     *
     * @param reqVO 分页请求
     * @return 字典数据
     */
    @GetMapping("/page")
    @OperateLog(module = "后台-字典", name = "分页密钥")
    @SaCheckPermission("system:rsa:query")
    public CommonResult<PageResult<RsaRespVO>> getDictDataPage(@Valid RsaPageVO reqVO) {
        return success(RsaConvert.INSTANCE.convertPage(rsaService.getRsaPage(reqVO)));
    }

    /**
     * 查询详细密钥
     *
     * @param id 字典数据id
     * @return 字典数据
     */
    @GetMapping
    @OperateLog(module = "后台-字典", name = "查询详细密钥")
    @SaCheckPermission("system:rsa:query")
    public CommonResult<RsaRespVO> getDictData(@RequestParam("id") Long id) {
        return success(RsaConvert.INSTANCE.convert(rsaService.getRsa(id)));
    }


}
