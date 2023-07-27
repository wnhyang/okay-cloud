package cn.wnhyang.okay.framework.common.enums;

import cn.hutool.core.util.ArrayUtil;
import cn.wnhyang.okay.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 全局用户类型枚举
 */
@AllArgsConstructor
@Getter
public enum UserTypeEnum implements IntArrayValuable {

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

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(UserTypeEnum::getType).toArray();

    /**
     * 类型
     */
    private final Integer type;

    public static UserTypeEnum valueOf(Integer value) {
        return ArrayUtil.firstMatch(userType -> userType.getType().equals(value), UserTypeEnum.values());
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
