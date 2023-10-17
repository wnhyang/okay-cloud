package cn.wnhyang.okay.system.vo.rsa;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wnhyang
 * @date 2023/10/17
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RsaUpdateReqVO extends RsaBaseVO{

    /**
     * 密钥主键
     */
    private Long id;

    /**
     * 备注
     */
    private String remark;
}
