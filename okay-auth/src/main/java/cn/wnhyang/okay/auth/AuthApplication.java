package cn.wnhyang.okay.auth;

import cn.wnhyang.okay.system.api.LoginLogApi;
import cn.wnhyang.okay.system.api.UserApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wnhyang
 * @date 2023/7/26
 **/
@EnableFeignClients(clients = {UserApi.class, LoginLogApi.class})
@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
