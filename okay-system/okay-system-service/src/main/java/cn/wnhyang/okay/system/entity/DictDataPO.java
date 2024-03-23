package cn.wnhyang.okay.system.entity;

import cn.wnhyang.okay.framework.mybatis.core.base.BasePO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 字典数据表
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_dict_data")
public class DictDataPO extends BasePO {

    private static final long serialVersionUID = 2306681901836679890L;
    /**
     * 字典数据主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 字典标签
     */
    @TableField("label")
    private String label;

    /**
     * 字典键值
     */
    @TableField("value")
    private String value;

    /**
     * 字典类型id
     */
    @TableField("dict_type")
    private String dictType;

    /**
     * 字典标签颜色
     */
    @TableField("color")
    private String color;

    /**
     * 状态（0正常 1停用）
     */
    @TableField("status")
    private Boolean status;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
