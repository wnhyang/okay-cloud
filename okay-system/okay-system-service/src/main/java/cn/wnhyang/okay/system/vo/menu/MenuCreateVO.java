package cn.wnhyang.okay.system.vo.menu;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wnhyang
 * @date 2023/8/10
 **/
@Data
public class MenuCreateVO {

    @NotNull(message = "菜单类型不能为空")
    private Integer type;

    @NotEmpty
    private String name;

    @Size(max = 200, message = "路由地址不能超过200个字符")
    private String path;

    @Size(max = 200, message = "组件路径不能超过255个字符")
    private String component;

    private String redirect;

    private Integer orderNo;

    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 50, message = "菜单名称长度不能超过50个字符")
    private String title;

    private String icon;

    private Boolean hideBreadcrumb;

    /**
     * 当前激活的菜单。用于配置详情页时左侧激活的菜单路径
     */
    private String currentActiveMenu;

    /**
     * 缓存
     */
    private Boolean keepalive;

    @Size(max = 100)
    private String permission;


    @NotNull(message = "父菜单 ID 不能为空")
    private Long parentId;

    private Boolean isExt;

    private Boolean isShow;

    @NotNull(message = "状态不能为空")
    private Boolean status;
}
