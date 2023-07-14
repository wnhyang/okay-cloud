package cn.wnhyang.okay.framework.core.handler;

import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.wnhyang.okay.framework.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;

/**
 * @author wnhyang
 * @date 2023/7/11
 **/
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    /**
     * @param request       请求
     * @param response      响应
     * @param authException 认证异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        log.debug("[commence][访问 URL({}) 时，没有登录]", request.getRequestURI(), authException);
        // 返回 401
        ServletUtils.writeJSON(response, CommonResult.error(UNAUTHORIZED));
    }
}
