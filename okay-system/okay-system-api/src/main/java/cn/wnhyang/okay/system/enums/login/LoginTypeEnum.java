package cn.wnhyang.okay.system.enums.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wnhyang
 * @date 2023/7/24
 **/
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
    /**
     * 账号密码登录
     */
    LOGIN_USERNAME(100),

    /**
     * 手机号密码登录
     */
    LOGIN_MOBILE(101),

    /**
     * 邮箱密码登录
     */
    LOGIN_EMAIL(102),

    /**
     * 自己退出
     */
    LOGOUT_SELF(120);

    private final Integer type;
}
