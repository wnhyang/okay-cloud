package cn.wnhyang.okay.auth.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wnhyang
 * @date 2023/8/3
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterReqVO extends AuthLoginReqVO {
    private Integer userType;
}
