package cn.wnhyang.okay.system.vo.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wnhyang
 * @date 2023/8/3
 **/
@Data
public class UserUpdateStatusVO {

    /**
     * 用户ID
     */
    @NotNull(message = "用户编号不能为空")
    private Long id;

    /**
     * 帐号状态（0正常 1停用）
     */
    private Boolean status;
}
