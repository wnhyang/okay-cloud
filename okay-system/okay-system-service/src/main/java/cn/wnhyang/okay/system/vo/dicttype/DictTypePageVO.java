package cn.wnhyang.okay.system.vo.dicttype;

import cn.hutool.core.date.DatePattern;
import cn.wnhyang.okay.framework.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author wnhyang
 * @date 2023/9/14
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class DictTypePageVO extends PageParam {

    private static final long serialVersionUID = -2268644414650536395L;

    private String name;

    private String type;

    private Boolean status;

    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime endTime;

}
