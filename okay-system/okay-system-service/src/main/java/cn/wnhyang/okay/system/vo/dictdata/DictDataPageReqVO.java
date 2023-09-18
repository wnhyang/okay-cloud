package cn.wnhyang.okay.system.vo.dictdata;

import cn.wnhyang.okay.framework.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;

/**
 * @author wnhyang
 * @date 2023/9/15
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class DictDataPageReqVO extends PageParam {

    @Size(max = 100, message = "字典标签长度不能超过100个字符")
    private String label;

    @Size(max = 100, message = "字典类型类型长度不能超过100个字符")
    private String dictType;

    private Integer status;
}
