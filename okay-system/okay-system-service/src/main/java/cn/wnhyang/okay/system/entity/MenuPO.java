package cn.wnhyang.okay.system.entity;

import cn.wnhyang.okay.framework.mybatis.core.base.BasePO;
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
public class MenuPO extends BasePO {

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
     * 菜单类型 目录0 菜单1 按钮2
     */
    @TableField("type")
    private Integer type;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 路由地址
     */
    @TableField("path")
    private String path;

    /**
     * 组件路径
     */
    @TableField("component")
    private String component;

    /**
     * 当设置 noredirect 的时候该路由在面包屑导航中不可被点击
     */
    @TableField("redirect")
    private String redirect;

    // meta start----------

    /**
     * 显示顺序
     */
    @TableField("order_no")
    private Integer orderNo;

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
     * 隐藏面包屑显示
     */
    @TableField("hide_breadcrumb")
    private Boolean hideBreadcrumb;

    /**
     * 当前激活的菜单。用于配置详情页时左侧激活的菜单路径
     */
    @TableField("current_active_menu")
    private String currentActiveMenu;

    /**
     * 缓存
     */
    @TableField("keepalive")
    private Boolean keepalive;

    // meta end----------

    /**
     * 权限标识
     */
    @TableField("permission")
    private String permission;

    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 是否外链
     */
    @TableField("is_ext")
    private Boolean isExt;

    /**
     * 是否显示
     */
    @TableField("is_show")
    private Boolean isShow;

    /**
     * 菜单状态
     */
    @TableField("status")
    private Boolean status;

}
