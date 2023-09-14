package cn.wnhyang.okay.system.vo.dicttype;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author wnhyang
 * @date 2023/9/13
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class DictTypeUpdateReqVO extends DictTypeBaseVO {

    @NotNull(message = "字典类型编号不能为空")
    private Long id;
}
