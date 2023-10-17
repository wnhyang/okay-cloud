package cn.wnhyang.okay.system.service;

import cn.wnhyang.okay.system.vo.rsa.RsaCreateReqVO;
import cn.wnhyang.okay.system.vo.rsa.RsaPairRespVO;

/**
 * 密钥表 服务类
 *
 * @author wnhyang
 * @since 2023/10/10
 */
public interface RsaService {

    /**
     * 生成密钥对
     * 默认生成不能设置补位方法和长度
     *
     * @return 密钥对
     */
    RsaPairRespVO generateKeyPair();

    /**
     * 新建密钥
     *
     * @param reqVO 密钥
     * @return id
     */
    Long createSecretKey(RsaCreateReqVO reqVO);
}
