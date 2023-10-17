package cn.wnhyang.okay.system.vo.rsa;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wnhyang
 * @date 2023/10/11
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RsaCreateReqVO extends RsaBaseVO {

    /**
     * 备注
     */
    private String remark;
}
