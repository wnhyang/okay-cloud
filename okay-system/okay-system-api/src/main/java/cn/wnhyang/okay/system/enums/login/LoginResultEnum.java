package cn.wnhyang.okay.system.enums.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wnhyang
 * @date 2023/7/25
 **/
@Getter
@AllArgsConstructor
public enum LoginResultEnum {

    /**
     * 成功
     */
    SUCCESS(0),

    /**
     * 账号或密码不正确
     */
    BAD_CREDENTIALS(10),

    /**
     * 邮箱或验证码不正确
     */
    BAD_MOBILE_CODE(11),

    /**
     * 邮箱或验证码不正确
     */
    BAD_EMAIL_CODE(12),

    /**
     * 用户被禁用
     */
    USER_DISABLED(20),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR(100);

    private final Integer result;
}
