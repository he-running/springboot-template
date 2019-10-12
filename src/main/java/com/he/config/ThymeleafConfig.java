package com.he.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * @author hesh
 * @date 2019/10/4
 * @des Thymeleaf配置
 */
@Configuration
public class ThymeleafConfig {

    @Bean
    SpringResourceTemplateResolver xmlTemplateResolver(ApplicationContext appCtx) {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();

        templateResolver.setApplicationContext(appCtx);
        templateResolver.setPrefix("classpath:/templates/");//指定模版前缀，即存放位置，默认是该地址
        templateResolver.setSuffix(".xml");//指定模版后缀
        templateResolver.setTemplateMode(TemplateMode.XML);//指定使用‘XML’模式
        templateResolver.setCharacterEncoding("UTF-8");//指定使用‘UTF-8’编码
        templateResolver.setCacheable(true);//开启缓存

        return templateResolver;
    }

    @Bean
    SpringTemplateEngine templateEngine(ApplicationContext appCtx) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.setTemplateResolver(xmlTemplateResolver(appCtx));
        templateEngine.addDialect(new CustomAttrDialect());//自定义方言
        return templateEngine;
    }

}
