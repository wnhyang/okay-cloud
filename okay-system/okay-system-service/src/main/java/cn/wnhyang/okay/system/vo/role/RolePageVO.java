package cn.wnhyang.okay.system.vo.role;

import cn.hutool.core.date.DatePattern;
import cn.wnhyang.okay.framework.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author wnhyang
 * @date 2023/8/10
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePageVO extends PageParam {

    private static final long serialVersionUID = -5653242641955460431L;

    private String name;

    private String value;

    private Boolean status;

    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime endTime;
}
