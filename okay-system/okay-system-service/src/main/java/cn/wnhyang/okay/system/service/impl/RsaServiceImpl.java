package cn.wnhyang.okay.system.service.impl;

import cn.hutool.crypto.asymmetric.RSA;
import cn.wnhyang.okay.system.convert.rsa.RsaConvert;
import cn.wnhyang.okay.system.entity.RsaDO;
import cn.wnhyang.okay.system.mapper.RsaMapper;
import cn.wnhyang.okay.system.service.RsaService;
import cn.wnhyang.okay.system.vo.rsa.RsaCreateReqVO;
import cn.wnhyang.okay.system.vo.rsa.RsaPairRespVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 密钥表 服务实现类
 *
 * @author wnhyang
 * @since 2023/10/10
 */
@Service
@RequiredArgsConstructor
public class RsaServiceImpl implements RsaService {

    private final RsaMapper rsaMapper;

    @Override
    public RsaPairRespVO generateKeyPair() {
        RSA rsa = new RSA();
        String publicKey = rsa.getPublicKeyBase64();
        String privateKey = rsa.getPrivateKeyBase64();
        RsaPairRespVO respVO = new RsaPairRespVO();
        respVO.setPublicKey(publicKey).setPrivateKey(privateKey);
        return respVO;
    }

    @Override
    public Long createSecretKey(RsaCreateReqVO reqVO) {
        RsaDO rsaDO = RsaConvert.INSTANCE.convert(reqVO);
        rsaMapper.insert(rsaDO);
        return rsaDO.getId();
    }
}
