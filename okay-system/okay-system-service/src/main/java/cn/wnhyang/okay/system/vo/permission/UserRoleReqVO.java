package cn.wnhyang.okay.system.vo.permission;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author wnhyang
 * @date 2023/11/16
 **/
@Data
public class UserRoleReqVO {

    @NotNull
    private Long userId;

    private Set<Long> roleIds;
}
