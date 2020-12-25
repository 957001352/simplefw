package com.fw.zuul.dhlk_zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableZuulProxy
@SpringBootApplication
public class DhlkZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(DhlkZuulApplication.class, args);
    }

}
