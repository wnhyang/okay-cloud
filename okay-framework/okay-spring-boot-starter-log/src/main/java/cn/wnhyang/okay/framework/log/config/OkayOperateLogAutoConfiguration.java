package cn.wnhyang.okay.framework.log.config;

import cn.wnhyang.okay.framework.log.core.aop.OperateLogAspect;
import cn.wnhyang.okay.framework.log.core.service.LogService;
import cn.wnhyang.okay.framework.log.core.service.OperateLogService;
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
    public LogService logService(OperateLogApi operateLogApi) {
        return new OperateLogService(operateLogApi);
    }

    @Bean
    public OperateLogAspect operateLogAspect(LogService logService) {
        OperateLogAspect operateLogAspect = new OperateLogAspect();
        operateLogAspect.setLogService(logService);
        return operateLogAspect;
    }

}
