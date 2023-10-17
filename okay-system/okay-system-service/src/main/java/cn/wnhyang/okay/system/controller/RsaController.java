package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.operatelog.core.annotation.OperateLog;
import cn.wnhyang.okay.system.service.RsaService;
import cn.wnhyang.okay.system.vo.rsa.RsaCreateReqVO;
import cn.wnhyang.okay.system.vo.rsa.RsaPairRespVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 密钥表
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
    public CommonResult<RsaPairRespVO> generateKeyPair() {
        return success(rsaService.generateKeyPair());
    }

    /**
     * 创建rsa密钥
     *
     * @param reqVO 密钥，可以是单个私钥或公钥
     * @return id
     */
    @PostMapping("/create")
    public CommonResult<Long> createRsa(@RequestBody RsaCreateReqVO reqVO) {
        return success(rsaService.createSecretKey(reqVO));
    }



}
