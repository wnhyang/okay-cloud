package cn.wnhyang.okay.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wnhyang
 * @date 2023/7/24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRespVO {

    private Long userId;

    private String accessToken;

    private String refreshToken;

    private LocalDateTime expiresTime;
}
