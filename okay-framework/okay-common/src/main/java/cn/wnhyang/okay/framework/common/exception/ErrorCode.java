package cn.wnhyang.okay.framework.common.exception;


import lombok.Data;

/**
 * 错误码对象
 * <p>
 * 全局错误码，占用 [0, 999], 参见 {@link GlobalErrorCode}
 * 业务异常错误码，占用 [1 000 000 000, +∞)，参见 {@link }
 * <p>
 * TODO 错误码设计成对象的原因，为未来的 i18 国际化做准备
 *
 * @author wnhyang
 */
@Data
public class ErrorCode {

    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

}
