package cn.wnhyang.okay.system.vo.dictdata;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author wnhyang
 * @date 2023/9/14
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class DictDataUpdateVO extends DictDataCreateVO {

    @NotNull(message = "字典数据编号不能为空")
    private Long id;
}
