package com.fw.service.fw_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.fw")
@MapperScan(basePackages= "com.fw.service.*.dao")
@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class FwServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FwServiceApplication.class, args);
    }

}
