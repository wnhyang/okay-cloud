package cn.wnhyang.okay.framework.mybatis.config;

import cn.wnhyang.okay.framework.mybatis.core.handler.MybatisPlusMetaObjectHandler;
import cn.wnhyang.okay.framework.web.core.service.LoginService;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author wnhyang
 * @date 2023/4/11
 **/
@AutoConfiguration
@MapperScan(value = "${okay.mybatis.mapper.base-package}", annotationClass = Mapper.class)
@Slf4j
public class OkayMybatisPlusAutoConfiguration {

    @Value("${okay.mybatis.login:false}")
    private Boolean login;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        log.info("[MybatisPlusInterceptor][初始化mybatisPlusInterceptor配置]");
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    @Bean
    public MetaObjectHandler defaultMetaObjectHandler(@Autowired(required = false) LoginService loginService) {
        log.info("[MetaObjectHandler][初始化defaultMetaObjectHandler配置]");
        // 自动填充参数类
        MybatisPlusMetaObjectHandler metaObjectHandler = new MybatisPlusMetaObjectHandler();
        metaObjectHandler.setLogin(login);
        metaObjectHandler.setLoginService(loginService);
        return metaObjectHandler;
    }

}
