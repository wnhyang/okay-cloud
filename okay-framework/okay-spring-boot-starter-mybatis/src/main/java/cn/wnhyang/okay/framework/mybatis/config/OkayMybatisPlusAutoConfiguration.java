package cn.wnhyang.okay.framework.mybatis.config;

import cn.wnhyang.okay.framework.mybatis.core.handler.MybatisPlusMetaObjectHandler;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author wnhyang
 * @date 2023/4/11
 **/
@AutoConfiguration
@Slf4j
public class OkayMybatisPlusAutoConfiguration {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        log.info("MybatisPlusAutoConfiguration.mybatisPlusInterceptor");
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    @Bean
    public MetaObjectHandler defaultMetaObjectHandler() {
        log.info("MybatisPlusAutoConfiguration.defaultMetaObjectHandler");
        // 自动填充参数类
        return new MybatisPlusMetaObjectHandler();
    }

}
