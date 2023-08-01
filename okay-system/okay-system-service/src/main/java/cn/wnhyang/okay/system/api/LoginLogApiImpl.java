package cn.wnhyang.okay.system.api;

import cn.wnhyang.okay.system.dto.loginlog.LoginLogCreateReqDTO;
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

    @Override
    public void createLoginLog(LoginLogCreateReqDTO reqDTO) {
        loginLogService.createLoginLog(reqDTO);
    }
}
