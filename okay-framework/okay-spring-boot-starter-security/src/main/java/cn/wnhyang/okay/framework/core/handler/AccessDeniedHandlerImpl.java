package cn.wnhyang.okay.framework.core.handler;

import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.util.ServletUtils;
import cn.wnhyang.okay.framework.core.util.SecurityFrameworkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.wnhyang.okay.framework.common.exception.enums.GlobalErrorCodeConstants.FORBIDDEN;

/**
 * @author wnhyang
 * @date 2023/7/11
 **/
@Slf4j
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    /**
     * @param request               请求
     * @param response              响应
     * @param accessDeniedException 访问拒绝异常
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        // 打印 warn 的原因是，不定期合并 warn，看看有没恶意破坏
        log.warn("[commence][访问 URL({}) 时，用户({}) 权限不够]", request.getRequestURI(),
                SecurityFrameworkUtils.getLoginUserId(), accessDeniedException);
        // 返回 403
        ServletUtils.writeJSON(response, CommonResult.error(FORBIDDEN));
    }
}
