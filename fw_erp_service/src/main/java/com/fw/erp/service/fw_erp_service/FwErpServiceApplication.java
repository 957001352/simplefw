package com.fw.erp.service.fw_erp_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan("com.fw")
public class FwErpServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FwErpServiceApplication.class, args);
    }

}
