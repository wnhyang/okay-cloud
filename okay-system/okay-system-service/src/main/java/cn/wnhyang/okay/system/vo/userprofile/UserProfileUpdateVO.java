package cn.wnhyang.okay.system.vo.userprofile;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * @author wnhyang
 * @date 2023/11/23
 **/
@Data
public class UserProfileUpdateVO {

    @Size(max = 30, message = "用户昵称长度不能超过 30 个字符")
    private String nickname;

    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过 50 个字符")
    private String email;

    @Length(min = 11, max = 11, message = "手机号长度必须 11 位")
    private String mobile;

    private Integer sex;

    private String remark;
}
