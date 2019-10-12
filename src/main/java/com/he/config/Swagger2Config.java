package com.he.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hesh
 * @date 2019/10/4
 * @des swagger2
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot2.x整合模版引擎")
                .description("Thymeleaf模版的XML模式,自定义方言属性；Freemarker的XML模式")
                .version("v1.0")
                .build();
    }


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.he.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
