package cn.wnhyang.okay.framework.common.enums;

import cn.hutool.core.util.ArrayUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局用户类型枚举
 *
 * @author wnhyang
 */
@AllArgsConstructor
@Getter
public enum UserType {

    /**
     * pc用户
     */
    PC(0),

    /**
     * app用户
     */
    APP(1),

    /**
     * 小程序用户
     */
    XCX(2),

    /**
     * 服务内部
     */
    RPC(9);

    /**
     * 类型
     */
    private final Integer type;

    public static UserType valueOf(Integer value) {
        return ArrayUtil.firstMatch(userType -> userType.getType().equals(value), UserType.values());
    }

}
