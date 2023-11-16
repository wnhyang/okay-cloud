package cn.wnhyang.okay.system.vo.permission;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author wnhyang
 * @date 2023/11/15
 **/
@Data
public class RoleMenuReqVO {

    @NotNull
    private Long roleId;

    private Set<Long> menuIds;
}
