package cn.wnhyang.okay.framework.satoken.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.wnhyang.okay.framework.satoken.core.service.StpInterfaceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author wnhyang
 * @date 2024/3/23
 **/
@AutoConfiguration
@Slf4j
public class SaTokenConfiguration {

    @Bean
    public StpInterface stpInterface() {
        return new StpInterfaceImpl();
    }
}
