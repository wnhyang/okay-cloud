package cn.wnhyang.okay.system.vo.secretkey;

import lombok.Data;

/**
 * @author wnhyang
 * @date 2023/10/10
 **/
@Data
public class SecretKeyBaseVO {

    /**
     * 密钥长度
     * dsa strength must be from 512 - 4096 and a multiple of 1024 above 1024
     */
    private Integer keySize;

    /**
     * 密钥类型
     */
    private String algorithm;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 公钥
     */
    private String publicKey;
}
