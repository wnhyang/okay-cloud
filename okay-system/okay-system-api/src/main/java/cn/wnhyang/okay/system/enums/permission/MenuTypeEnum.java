package cn.wnhyang.okay.system.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wnhyang
 * @date 2023/8/10
 **/
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    /**
     * 目录
     */
    DIR(1),

    /**
     * 菜单
     */
    MENU(2),

    /**
     * 按钮
     */
    BUTTON(3);

    /**
     * 菜单类型
     */
    private final Integer type;
}
