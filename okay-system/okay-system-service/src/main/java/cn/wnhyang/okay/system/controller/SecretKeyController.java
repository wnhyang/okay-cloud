package cn.wnhyang.okay.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.operatelog.core.annotation.OperateLog;
import cn.wnhyang.okay.system.service.SecretKeyService;
import cn.wnhyang.okay.system.vo.secretkey.SecretKeyCreateReqVO;
import cn.wnhyang.okay.system.vo.secretkey.SecretKeyPairReqVO;
import cn.wnhyang.okay.system.vo.secretkey.SecretKeyPairRespVO;
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
@RequestMapping("/system/secretKey")
@RequiredArgsConstructor
public class SecretKeyController {

    private final SecretKeyService secretKeyService;

    /**
     * 生成密钥
     *
     * @param reqVO 密钥类型
     * @return 密钥对
     */
    @PostMapping("/generate")
    @OperateLog(module = "后台-密钥", name = "生成密钥")
    @SaCheckPermission("system:secretKey:generate")
    public CommonResult<SecretKeyPairRespVO> generateKeyPair(@RequestBody SecretKeyPairReqVO reqVO) {
        return success(secretKeyService.generateKeyPair(reqVO));
    }

    @PostMapping("/create")
    public CommonResult<Boolean> createSecretKey(@RequestBody SecretKeyCreateReqVO reqVO) {
        return success(true);
    }

}
