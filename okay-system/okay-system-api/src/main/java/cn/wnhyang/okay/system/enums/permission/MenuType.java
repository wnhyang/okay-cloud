package cn.wnhyang.okay.system.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wnhyang
 * @date 2023/8/10
 **/
@Getter
@AllArgsConstructor
public enum MenuType {

    /**
     * 目录
     */
    DIR(0),

    /**
     * 菜单
     */
    MENU(1),

    /**
     * 按钮
     */
    BUTTON(2);

    /**
     * 菜单类型
     */
    private final Integer type;
}
