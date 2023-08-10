package cn.wnhyang.okay.system.entity;

import cn.wnhyang.okay.framework.mybatis.core.base.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 菜单权限表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu")
public class MenuDO extends BaseDO {

    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 权限标识
     */
    @TableField("permission")
    private String permission;

    /**
     * 菜单类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 显示顺序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 路由地址
     */
    @TableField("path")
    private String path;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 组件路径
     */
    @TableField("component")
    private String component;

    /**
     * 组件名
     */
    @TableField("component_name")
    private String componentName;

    /**
     * 菜单状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 是否可见
     */
    @TableField("visible")
    private Boolean visible;

    /**
     * 是否缓存
     */
    @TableField("keep_alive")
    private Boolean keepAlive;

    /**
     * 是否总是显示
     */
    @TableField("always_show")
    private Boolean alwaysShow;
}
