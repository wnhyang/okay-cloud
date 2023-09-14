package cn.wnhyang.okay.framework.security.core.rpc;

import cn.dev33.satoken.same.SaSameUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author wnhyang
 * @date 2023/7/29
 **/
public class FeignInterceptor implements RequestInterceptor {

    /**
     * 为 Feign 的 RCP调用 添加请求头Same-Token
     *
     * @param requestTemplate r
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken());
    }
}