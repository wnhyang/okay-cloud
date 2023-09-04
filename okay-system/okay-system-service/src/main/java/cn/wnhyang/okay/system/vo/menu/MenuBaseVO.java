package cn.wnhyang.okay.system.vo.menu;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wnhyang
 * @date 2023/8/10
 **/
@Data
public class MenuBaseVO {

    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 50, message = "菜单名称长度不能超过50个字符")
    private String name;

    @Size(max = 100)
    private String permission;

    @NotNull(message = "菜单类型不能为空")
    private Integer type;

    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    @NotNull(message = "父菜单 ID 不能为空")
    private Long parentId;

    @Size(max = 200, message = "路由地址不能超过200个字符")
    private String path;

    private String icon;

    @Size(max = 200, message = "组件路径不能超过255个字符")
    private String component;

    private String title;

    @NotNull(message = "状态不能为空")
    private Integer status;

    private Boolean hidden;

    private Boolean noCache;

    private Boolean alwaysShow;
}
