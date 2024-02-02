package cn.wnhyang.okay.framework.log.config;

import cn.wnhyang.okay.framework.log.core.aop.OperateLogAspect;
import cn.wnhyang.okay.framework.web.core.service.LoginService;
import cn.wnhyang.okay.system.api.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author wnhyang
 * @date 2023/5/31
 **/
@AutoConfiguration
public class OkayOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect(OperateLogApi operateLogApi, LoginService loginService) {
        OperateLogAspect operateLogAspect = new OperateLogAspect();
        operateLogAspect.setOperateLogApi(operateLogApi);
        operateLogAspect.setLoginService(loginService);
        return operateLogAspect;
    }

}
