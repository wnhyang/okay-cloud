package cn.wnhyang.okay.system.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.wnhyang.okay.framework.common.enums.CryptoConstants;
import cn.wnhyang.okay.system.service.SecretKeyService;
import cn.wnhyang.okay.system.vo.secretkey.SecretKeyPairReqVO;
import cn.wnhyang.okay.system.vo.secretkey.SecretKeyPairRespVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyPair;

import static cn.wnhyang.okay.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.wnhyang.okay.system.enums.ErrorCodeConstants.SECRET_KEY_NOT_SUPPORT;

/**
 * 密钥表 服务实现类
 *
 * @author wnhyang
 * @since 2023/10/10
 */
@Service
@RequiredArgsConstructor
public class SecretKeyServiceImpl implements SecretKeyService {

    @Override
    public SecretKeyPairRespVO generateKeyPair(SecretKeyPairReqVO reqVO) {
        String algorithm = reqVO.getAlgorithm();
        Integer keySize = reqVO.getKeySize();
        if (CryptoConstants.RSA.equalsIgnoreCase(algorithm) || CryptoConstants.DSA.equalsIgnoreCase(algorithm)) {
            KeyPair keyPair = SecureUtil.generateKeyPair(algorithm, keySize);
            String publicKey = Base64.encode(keyPair.getPublic().getEncoded());
            String privateKey = Base64.encode(keyPair.getPrivate().getEncoded());
            SecretKeyPairRespVO respVO = new SecretKeyPairRespVO();
            respVO.setAlgorithm(algorithm.toUpperCase()).setKeySize(keySize)
                    .setPublicKey(publicKey).setPrivateKey(privateKey);
            return respVO;
        }
        throw exception(SECRET_KEY_NOT_SUPPORT);
    }
}
