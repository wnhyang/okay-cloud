package cn.wnhyang.okay.framework.satoken.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.wnhyang.okay.framework.satoken.core.service.StpInterfaceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author wnhyang
 * @date 2023/7/28
 **/
@AutoConfiguration
public class SaTokenConfiguration {

    @Bean
    public StpInterface stpInterface() {
        return new StpInterfaceImpl();
    }
}
