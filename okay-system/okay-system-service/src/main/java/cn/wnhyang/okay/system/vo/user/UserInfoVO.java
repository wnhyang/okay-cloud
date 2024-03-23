package cn.wnhyang.okay.system.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * @author wnhyang
 * @date 2023/9/5
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoVO {

    private UserVO user;

    private Set<String> roles;

    private Set<String> permissions;

    private List<MenuVO> menus;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserVO {
        private Long id;

        private String username;

        private String nickname;

        private String avatar;

        private String remark;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MenuVO {

        private Long id;

        /**
         * 菜单类型 目录0 菜单1 按钮2
         */
        private Integer type;

        /**
         * 菜单名称
         */
        private String name;

        /**
         * 路由地址
         */
        private String path;

        /**
         * 组件路径
         */
        private String component;

        /**
         * 当设置 noredirect 的时候该路由在面包屑导航中不可被点击
         */
        private String redirect;

        // meta start----------

        /**
         * 显示顺序
         */
        private Integer orderNo;

        /**
         * 组件名
         */
        private String title;

        /**
         * 菜单图标
         */
        private String icon;

        /**
         * 隐藏面包屑显示
         */
        private Boolean hideBreadcrumb;

        /**
         * 当前激活的菜单。用于配置详情页时左侧激活的菜单路径
         */
        private String currentActiveMenu;

        /**
         * 缓存
         */
        private Boolean keepalive;

        // meta end----------

        /**
         * 权限标识
         */
        private String permission;

        /**
         * 父菜单ID
         */
        private Long parentId;

        /**
         * 是否显示
         */
        private Boolean isShow;

        private Boolean isExt;

        /**
         * 菜单状态
         */
        private Boolean status;

        /**
         * 子路由
         */
        private List<MenuVO> children;

    }
}
