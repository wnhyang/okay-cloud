package cn.wnhyang.okay.system.vo.permission;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author wnhyang
 * @date 2023/11/15
 **/
@Data
public class RoleMenuVO {

    @NotNull(message = "角色编号不能为空")
    private Long roleId;

    private Set<Long> menuIds;
}
