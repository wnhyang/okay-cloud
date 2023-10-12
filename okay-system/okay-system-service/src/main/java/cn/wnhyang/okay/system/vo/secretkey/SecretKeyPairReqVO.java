package cn.wnhyang.okay.system.vo.secretkey;

import lombok.Data;

/**
 * @author wnhyang
 * @date 2023/10/11
 **/
@Data
public class SecretKeyPairReqVO {

    /**
     * 密钥长度
     */
    private Integer keySize;

    /**
     * 密钥类型
     */
    private String algorithm;
}
