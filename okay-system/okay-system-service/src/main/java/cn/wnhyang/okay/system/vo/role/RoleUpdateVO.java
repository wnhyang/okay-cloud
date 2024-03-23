package cn.wnhyang.okay.system.vo.role;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author wnhyang
 * @date 2023/8/10
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleUpdateVO extends RoleCreateVO {

    @NotNull(message = "角色编号不能为空")
    private Long id;
}
