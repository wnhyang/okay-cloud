package cn.wnhyang.okay.system.entity;

import cn.wnhyang.okay.framework.mybatis.core.base.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 字典类型表
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict_type")
public class DictTypeDO extends BaseDO {

    /**
     * 字典类型主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典名称
     */
    @TableField("name")
    private String name;

    /**
     * 字典类型
     */
    @TableField("type")
    private String type;

    /**
     * 状态（0正常 1停用）
     */
    @TableField("status")
    private Byte status;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
