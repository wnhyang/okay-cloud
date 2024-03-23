package cn.wnhyang.okay.system.vo.role;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author wnhyang
 * @date 2023/8/10
 **/
@Data
public class RoleUpdateStatusVO {

    @NotNull(message = "角色编号不能为空")
    private Long id;

    @NotNull(message = "状态不能为空")
    private Boolean status;
}
