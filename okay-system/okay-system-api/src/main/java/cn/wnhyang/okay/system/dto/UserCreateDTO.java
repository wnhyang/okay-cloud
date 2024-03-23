package cn.wnhyang.okay.system.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wnhyang
 * @date 2023/8/3
 **/
@Data
public class UserCreateDTO implements Serializable {

    private static final long serialVersionUID = 451667800659896712L;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 描述
     */
    private String remark;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 用户性别
     */
    private Integer sex;


}
