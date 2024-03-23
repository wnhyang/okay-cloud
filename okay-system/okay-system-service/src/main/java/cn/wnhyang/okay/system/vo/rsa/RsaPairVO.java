package cn.wnhyang.okay.system.vo.rsa;

import lombok.Data;

/**
 * @author wnhyang
 * @date 2023/10/10
 **/
@Data
public class RsaPairVO {

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 公钥
     */
    private String publicKey;
}
