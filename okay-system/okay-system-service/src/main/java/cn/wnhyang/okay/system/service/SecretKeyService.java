package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.system.vo.secretkey.SecretKeyPairReqVO;
import cn.wnhyang.okay.system.vo.secretkey.SecretKeyPairRespVO;

/**
 * 密钥表 服务类
 *
 * @author wnhyang
 * @since 2023/10/10
 */
public interface SecretKeyService {

    /**
     * 生成密钥对
     *
     * @param reqVO 密钥要求
     * @return 密钥对
     */
    SecretKeyPairRespVO generateKeyPair(SecretKeyPairReqVO reqVO);
}
