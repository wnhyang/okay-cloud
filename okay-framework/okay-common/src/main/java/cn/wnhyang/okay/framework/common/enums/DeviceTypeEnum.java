package cn.wnhyang.okay.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wnhyang
 * @date 2023/7/27
 **/
@Getter
@AllArgsConstructor
public enum DeviceTypeEnum {

    /**
     * pc
     */
    PC("pc"),

    /**
     * app
     */
    APP("app"),

    /**
     * xcx
     */
    XCX("xcx");

    private final String device;
}
