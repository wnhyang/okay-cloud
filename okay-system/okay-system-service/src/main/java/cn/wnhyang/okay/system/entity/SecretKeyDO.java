package cn.wnhyang.okay.system.entity;

import cn.wnhyang.okay.framework.mybatis.core.base.BaseDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 密钥表
 *
 * @author wnhyang
 * @since 2023/10/10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_secret_key")
public class SecretKeyDO extends BaseDO {

    private static final long serialVersionUID = -2948312666702883764L;

    /**
     * 密钥主键
     */
    @TableId("id")
    private Long id;

    /**
     * 密钥类型
     */
    @TableField("algorithm")
    private String algorithm;

    /**
     * 密钥长度
     */
    @TableField("key_size")
    private Integer keySize;

    /**
     * 私钥
     */
    @TableField("private_key")
    private String privateKey;

    /**
     * 公钥
     */
    @TableField("public_key")
    private String publicKey;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
