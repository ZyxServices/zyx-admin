package com.zyx.document;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.zyx.jopo.BaseResponse;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //Loads the spring beans required by the framework
public class MySwaggerConfig {


    private ApiInfo apiInfo() {
        Contact contact = new Contact("chenkaihua", "https://github.com/ichenkaihua/ssm-easy-template", "admin@chenkaihua.com");
        return new ApiInfoBuilder()
                .title("ssm_easy_template API接口")
                .description("SSM-Easy-Template 是一个J2Ee项目快速开发脚手架，" +
                        "集成了最常用的框架,适用于Restfull 架构风格Web Service接口开发。项目使用最灵活的构建工具-gradle" +
                        "，加入了常用的gradle插件(gretty,flydb，mybatis generator),。")
                .contact(contact)
                .version("2.0")
                .build();
    }

    private Predicate<String> userPaths() {
        return or(
                regex("/users.*")
        );
    }

    @Bean
    public Docket liveApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("live")
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.zyx.controller.live"))
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build()
                .apiInfo(liveApiInfo());
    }
    @Bean
    public Docket pgApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("pg")
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.zyx.controller.pg"))
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build()
                .apiInfo(pgApiInfo());
    }
    @Bean
    public Docket userApi() {
        Set<String> set = new HashSet<String>();

        com.fasterxml.classmate.TypeResolver typeResolver = new com.fasterxml.classmate.TypeResolver();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("user-api")
                .apiInfo(apiInfo())
                .select()
                .paths(userPaths())
                .build().useDefaultResponseMessages(false)
                .genericModelSubstitutes(BaseResponse.class)
                .forCodeGeneration(true)
                ;
        return docket;
    }


    private ApiInfo liveApiInfo() {
        ApiInfo apiInfo = new ApiInfo("直播接口API",//大标题
                "图文直播，视频直播",//小标题
                "0.1",//版本
                "成都term",
                "邓清海",//作者
                "智悠行",//链接显示文字
                "http://112.74.112.143:8081/ui/Delta/index.html "//网站链接
        );

        return apiInfo;
    }
    private ApiInfo pgApiInfo() {
        ApiInfo apiInfo = new ApiInfo("操场Api",//大标题
                "圈子，动态，帖子相关api",//小标题
                "0.1",//版本
                "暂无",
                new Contact("肖伟", "暂无", "xiaowei@perfect-cn.cn"),//作者
                "智悠行",//链接显示文字
                "http://112.74.112.143:8081/ui/Delta/index.html "//网站链接
        );

        return apiInfo;
    }

}