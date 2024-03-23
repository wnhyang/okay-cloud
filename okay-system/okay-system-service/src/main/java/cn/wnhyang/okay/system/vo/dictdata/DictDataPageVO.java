package cn.wnhyang.okay.system.vo.dictdata;

import cn.wnhyang.okay.framework.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wnhyang
 * @date 2023/9/15
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class DictDataPageVO extends PageParam {

    private static final long serialVersionUID = 7765456791884144537L;

    private String label;

    private String dictType;

    private Boolean status;
}
