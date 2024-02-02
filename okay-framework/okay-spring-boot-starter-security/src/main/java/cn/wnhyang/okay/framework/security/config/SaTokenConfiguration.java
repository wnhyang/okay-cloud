package cn.wnhyang.okay.framework.security.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.wnhyang.okay.framework.security.core.service.LoginServiceImpl;
import cn.wnhyang.okay.framework.security.core.service.StpInterfaceImpl;
import cn.wnhyang.okay.framework.web.core.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author wnhyang
 * @date 2023/7/28
 **/
@AutoConfiguration
@Slf4j
public class SaTokenConfiguration {

    @Bean
    public LoginService loginService() {
        return new LoginServiceImpl();
    }

    @Bean
    public StpInterface stpInterface(LoginService loginService) {
        StpInterfaceImpl stpInterface = new StpInterfaceImpl();
        stpInterface.setLoginService(loginService);
        return stpInterface;
    }
}
