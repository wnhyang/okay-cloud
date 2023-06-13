package cn.wnhyang.okay.framework.web.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wnhyang
 * @date 2023/5/31
 **/
@AutoConfiguration
@EnableConfigurationProperties({WebProperties.class})
public class OkayWebAutoConfiguration implements WebMvcConfigurer {


}
