package cn.wnhyang.okay.system.vo.dicttype;

import cn.hutool.core.date.DatePattern;
import cn.wnhyang.okay.framework.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author wnhyang
 * @date 2023/9/14
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class DictTypePageReqVO extends PageParam {

    private static final long serialVersionUID = -2268644414650536395L;

    private String name;

    @Size(max = 100, message = "字典类型类型长度不能超过100个字符")
    private String type;

    private Integer status;

    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime[] createTime;

}
