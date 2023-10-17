package cn.wnhyang.okay.system.vo.rsa;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author wnhyang
 * @date 2023/10/10
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RsaRespVO extends RsaBaseVO {

    /**
     * 密钥主键
     */
    private Long id;

    /**
     * 备注
     */
    private String remark;

    private LocalDateTime createTime;
}
