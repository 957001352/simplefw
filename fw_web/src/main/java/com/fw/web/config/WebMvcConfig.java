package com.fw.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 版本        修改时间        编者      备注
 * V1.0        ------        jpdong    原始版本
 * 文件说明:
 **/
@Configuration
public class WebMvcConfig  extends WebMvcConfigurerAdapter {
    @Value("${attachment.path}")
    private String attachmentPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");


        registry.addResourceHandler("/attach/**").addResourceLocations("file:"+attachmentPath);
    }
}
