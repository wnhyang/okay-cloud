package cn.wnhyang.okay.system.vo.loginlog;

import cn.hutool.core.date.DatePattern;
import cn.wnhyang.okay.framework.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author wnhyang
 * @date 2023/8/15
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginLogPageReqVO extends PageParam {

    private String userIp;

    private String account;

    private Boolean status;

    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime[] createTime;
}