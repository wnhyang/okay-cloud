package cn.wnhyang.okay.gateway.filter;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.wnhyang.okay.framework.common.exception.enums.GlobalErrorCodeConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wnhyang
 * @date 2023/7/28
 **/
@Configuration
public class AuthFilter {


    /**
     * 注册 Sa-Token全局过滤器
     **/
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截全部path
                .addInclude("/**")
                // 开放地址
                .addExclude("/favicon.ico")
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {
                    // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
                    SaRouter.match("/**").notMatch("/auth/**").check(r -> StpUtil.checkLogin());

                    // 更多匹配 ...  */
                })
                // 异常处理方法：每次setAuth函数出现异常时进入
                .setError(e -> SaResult.error("认证失败，无法访问系统资源").setCode(GlobalErrorCodeConstants.UNAUTHORIZED.getCode()));
    }
}
