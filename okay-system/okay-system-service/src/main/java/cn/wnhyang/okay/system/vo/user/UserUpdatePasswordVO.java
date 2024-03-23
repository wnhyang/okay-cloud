package cn.wnhyang.okay.system.vo.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author wnhyang
 * @date 2023/8/3
 **/
@Data
public class UserUpdatePasswordVO {

    /**
     * 用户ID
     */
    @NotNull(message = "用户编号不能为空")
    private Long id;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;
}
