package cn.wnhyang.okay.framework.log.config;

import cn.wnhyang.okay.system.api.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wnhyang
 * @date 2023/6/8
 **/
@AutoConfiguration
@EnableFeignClients(clients = {OperateLogApi.class}) // 主要是引入相关的 API 服务
public class OkayOperateLogRpcAutoConfiguration {
}
