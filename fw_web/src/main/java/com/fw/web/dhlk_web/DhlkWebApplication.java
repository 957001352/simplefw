package com.fw.web.dhlk_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.fw.web.*.service"})
@ComponentScan("com.fw.web")
public class DhlkWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DhlkWebApplication.class, args);
    }

}
