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
public class UserInfoRespVO {

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

        private Long parentId;

        private String name;

        private String component;

        private String path;

        private String redirect;

        private Boolean hidden;

        private Boolean alwaysShow;

        private String title;

        private String icon;

        private Boolean noCache;

        private Integer status;

        /**
         * 子路由
         */
        private List<MenuVO> children;

    }
}
