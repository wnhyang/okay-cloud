package cn.wnhyang.okay.framework.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author wnhyang
 * @date 2023/7/6
 **/
@AutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityAutoConfiguration {

    /**
     * 认证失败处理类 Bean
     */
    private final AuthenticationEntryPoint authenticationEntryPoint;


    /**
     * 权限不够处理器 Bean
     */
    private final AccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 由于 Spring Security 创建 AuthenticationManager 对象时，没声明 @Bean 注解，导致无法被注入
     * 通过覆写父类的该方法，添加 @Bean 注解，解决该问题
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }




}
