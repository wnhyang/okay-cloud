package cn.wnhyang.okay.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wnhyang
 * @date 2023/8/8
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobileLoginVO {

    /**
     * 手机号码
     */
    private String mobile;
}
