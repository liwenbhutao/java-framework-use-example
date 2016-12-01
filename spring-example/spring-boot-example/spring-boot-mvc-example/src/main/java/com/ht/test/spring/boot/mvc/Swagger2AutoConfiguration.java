package com.ht.test.spring.boot.mvc;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author hutao <胡涛, 123406956@qq.com>
 * @version v1.0
 * @project test-spring-boot-example
 * @Description
 * @encoding UTF-8
 * @date 2016/12/1
 * @time 上午9:15
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Configuration
@EnableSwagger2
public class Swagger2AutoConfiguration {
    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("full-petstore")
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .select()
                .paths(petstorePaths())
                .build();
    }

    private Predicate<String> petstorePaths() {
        return or(
                regex("/domain/.*")
        );
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Springfox REST API")
                .description("Descriptions.")
                .termsOfServiceUrl("http://springfox.io")
                .contact("springfox")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }

    @Bean
    public Docket configSpringfoxDocket_all() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .protocols(Sets.newHashSet("http", "https"))
                .forCodeGeneration(true)
                .select().paths(regex(".*"))
                .build();
    }
}
