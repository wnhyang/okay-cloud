package cn.wnhyang.okay.system.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author wnhyang
 * @date 2023/7/25
 **/
@Data
public class LoginLogCreateDTO implements Serializable {

    private static final long serialVersionUID = -3025051336842878235L;

    @NotNull(message = "日志类型不能为空")
    private Integer loginType;

    private Long userId;

    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    @NotBlank(message = "用户账号不能为空")
    @Size(max = 30, message = "用户账号长度不能超过30个字符")
    private String account;

    @NotNull(message = "登录结果不能为空")
    private Integer result;

    @NotEmpty(message = "用户 IP 不能为空")
    private String userIp;

    private String userAgent;
}
