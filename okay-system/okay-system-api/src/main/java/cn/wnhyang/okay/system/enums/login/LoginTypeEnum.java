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
     * 手机号验证码登录
     */
    LOGIN_MOBILE_CODE(103),

    /**
     * 邮箱验证码登录
     */
    LOGIN_EMAIL_CODE(104),

    /**
     * 自己退出
     */
    LOGOUT_SELF(120);

    private final Integer type;
}
