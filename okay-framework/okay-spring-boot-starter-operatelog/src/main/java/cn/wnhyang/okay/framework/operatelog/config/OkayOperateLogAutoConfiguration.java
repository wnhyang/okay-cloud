package cn.wnhyang.okay.framework.operatelog.config;

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
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
