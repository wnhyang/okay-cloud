package cn.wnhyang.okay.framework.security.config;

import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.same.SaSameUtil;
import cn.dev33.satoken.util.SaResult;
import cn.wnhyang.okay.framework.common.exception.enums.GlobalErrorCodeConstants;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wnhyang
 * @date 2023/7/29
 **/
@AutoConfiguration
public class SecurityConfiguration implements WebMvcConfigurer {
    // 注册 Sa-Token 全局过滤器
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/favicon.ico", "/rpc-api/**")
                .setAuth(obj -> {
                    // 校验 Same-Token 身份凭证     —— 以下两句代码可简化为：SaSameUtil.checkCurrentRequestToken();
                    SaSameUtil.checkCurrentRequestToken();
                })
                .setError(e -> SaResult.error("认证失败，无法访问系统资源").setCode(GlobalErrorCodeConstants.UNAUTHORIZED.getCode()));
    }
}
