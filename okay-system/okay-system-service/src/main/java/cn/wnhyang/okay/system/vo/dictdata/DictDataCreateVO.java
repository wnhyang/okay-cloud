package cn.wnhyang.okay.system.vo.dictdata;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wnhyang
 * @date 2023/9/14
 **/
@Data
public class DictDataCreateVO {

    private Integer sort;

    @NotBlank(message = "字典标签不能为空")
    @Size(max = 100, message = "字典标签长度不能超过100个字符")
    private String label;

    @NotBlank(message = "字典键值不能为空")
    @Size(max = 100, message = "字典键值长度不能超过100个字符")
    private String value;

    @NotBlank(message = "字典类型不能为空")
    @Size(max = 100, message = "字典类型长度不能超过100个字符")
    private String dictType;

    private String color;

    @NotNull(message = "状态不能为空")
    private Boolean status;

    private String remark;
}
