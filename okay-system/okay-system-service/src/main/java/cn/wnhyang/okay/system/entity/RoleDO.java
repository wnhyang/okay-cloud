package cn.wnhyang.okay.system.entity;

import cn.wnhyang.okay.framework.mybatis.core.base.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * <p>
 * 角色信息表
 * </p>
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
public class RoleDO extends BaseDO {

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
    @TableField("code")
    private String code;

    /**
     * 显示顺序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField("status")
    private Integer status;

    /**
     * 角色类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
