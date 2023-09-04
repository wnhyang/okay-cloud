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
     * 菜单编号 - 根节点
     */
    public static final Long ID_ROOT = 0L;

    private static final long serialVersionUID = 986081501377397378L;

    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 组件路径
     */
    @TableField("component")
    private String component;

    /**
     * 路由地址
     */
    @TableField("path")
    private String path;

    /**
     * 当设置 noredirect 的时候该路由在面包屑导航中不可被点击
     */
    @TableField("redirect")
    private String redirect;

    // 下面是菜单meta

    /**
     * 是否隐藏
     */
    @TableField("hidden")
    private Boolean hidden;

    /**
     * 是否总是显示
     */
    @TableField("always_show")
    private Boolean alwaysShow;

    /**
     * 组件名
     */
    @TableField("title")
    private String title;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 是否可缓存
     */
    @TableField("no_cache")
    private Boolean noCache;

    /**
     * 菜单状态
     */
    @TableField("status")
    private Integer status;

}
