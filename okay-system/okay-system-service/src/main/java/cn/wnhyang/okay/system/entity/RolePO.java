package cn.wnhyang.okay.system.entity;

import cn.wnhyang.okay.framework.mybatis.core.base.BasePO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 角色信息表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
public class RolePO extends BasePO {

    private static final long serialVersionUID = 6734459350185846076L;

    /**
     * 角色ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @TableField("name")
    private String name;

    /**
     * 角色权限字符串
     */
    @TableField("value")
    private String value;

    /**
     * 显示顺序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField("status")
    private Boolean status;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
