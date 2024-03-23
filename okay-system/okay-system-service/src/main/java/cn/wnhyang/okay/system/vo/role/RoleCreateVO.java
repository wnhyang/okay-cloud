package cn.wnhyang.okay.system.vo.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wnhyang
 * @date 2023/8/10
 **/
@Data
public class RoleCreateVO {

    @NotBlank(message = "角色名称不能为空")
    @Size(max = 30, message = "角色名称长度不能超过30个字符")
    private String name;

    @NotBlank(message = "角色标志不能为空")
    @Size(max = 100, message = "角色标志长度不能超过100个字符")
    private String value;

    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    private String remark;
}
