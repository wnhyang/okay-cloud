package cn.wnhyang.okay.system.vo.operatelog;

import cn.hutool.core.date.DatePattern;
import cn.wnhyang.okay.framework.common.pojo.PageParam;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author wnhyang
 * @date 2023/8/15
 **/
@Data
public class OperateLogPageReqVO extends PageParam {

    private String module;

    private String userNickname;

    private Integer type;

    private Boolean success;

    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime[] startTime;
}