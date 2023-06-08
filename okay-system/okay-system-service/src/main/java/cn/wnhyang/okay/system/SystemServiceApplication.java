package cn.wnhyang.okay.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wnhyang
 * @date 2023/5/19
 **/
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class SystemServiceApplication {
    public static void main(String[] args) {
        try {
            log.info("开始启动。。。");
            SpringApplication.run(SystemServiceApplication.class, args);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
