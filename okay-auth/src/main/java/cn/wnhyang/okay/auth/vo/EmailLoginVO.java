package cn.wnhyang.okay.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author wnhyang
 * @date 2023/8/8
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailLoginVO {

    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 验证码
     */
    @NotEmpty
    @Size(min = 6, max = 6, message = "验证码长度为 6 位")
    private String code;
}
