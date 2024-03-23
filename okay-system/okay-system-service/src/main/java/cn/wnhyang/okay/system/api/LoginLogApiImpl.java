package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.system.dto.LoginLogCreateDTO;
import cn.wnhyang.okay.system.service.LoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录日志api
 *
 * @author wnhyang
 * @date 2023/7/26
 **/
@RestController
@Validated
@RequiredArgsConstructor
public class LoginLogApiImpl implements LoginLogApi {

    private final LoginLogService loginLogService;

    /**
     * 创建登录日志
     *
     * @param reqDTO 日志信息
     */
    @Override
    public void createLoginLog(LoginLogCreateDTO reqDTO) {
        loginLogService.createLoginLog(reqDTO);
    }
}
