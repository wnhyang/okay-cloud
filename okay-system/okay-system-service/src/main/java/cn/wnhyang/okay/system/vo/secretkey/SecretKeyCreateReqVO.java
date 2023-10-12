package cn.wnhyang.okay.system.vo.secretkey;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wnhyang
 * @date 2023/10/11
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SecretKeyCreateReqVO extends SecretKeyBaseVO{

    /**
     * 备注
     */
    private String remark;
}
