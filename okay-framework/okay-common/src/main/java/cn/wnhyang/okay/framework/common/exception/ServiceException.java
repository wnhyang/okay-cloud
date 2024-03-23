package cn.wnhyang.okay.framework.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;


/**
 * 业务逻辑异常 Exception
 *
 * @author wnhyang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 7088760165004275908L;

    /**
     * 业务错误码
     */
    @Getter
    private Integer code;
    /**
     * 错误提示
     */
    private String message;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException() {
    }

    public ServiceException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ServiceException setMessage(String message) {
        this.message = message;
        return this;
    }

}
