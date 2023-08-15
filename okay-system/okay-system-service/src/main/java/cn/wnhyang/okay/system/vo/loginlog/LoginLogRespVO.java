package cn.wnhyang.okay.system.vo.loginlog;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author wnhyang
 * @date 2023/8/15
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LoginLogRespVO extends LoginLogBaseVO {

    private Long id;

    private Long userId;

    @NotNull(message = "用户类型不能为空")
    private Integer userType;

    private LocalDateTime createTime;
}
