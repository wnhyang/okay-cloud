package cn.wnhyang.okay.system.vo.userprofile;

import cn.wnhyang.okay.system.vo.user.UserBaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wnhyang
 * @date 2023/11/23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserProfileRespVO extends UserBaseVO {

    private Long id;

    private Integer status;

    private String loginIp;

    private LocalDateTime loginDate;

    private LocalDateTime createTime;
}
