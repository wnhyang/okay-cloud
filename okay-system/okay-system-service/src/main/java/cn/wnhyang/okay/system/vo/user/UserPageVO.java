package cn.wnhyang.okay.system.vo.user;

import cn.hutool.core.date.DatePattern;
import cn.wnhyang.okay.framework.common.pojo.PageParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author wnhyang
 * @date 2023/8/9
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserPageVO extends PageParam {

    private static final long serialVersionUID = 1382984856072227773L;

    private String username;

    private String mobile;

    private Boolean status;

    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime endTime;
}
