package com.ybw.appsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = {"com.ybw.appsys.mapper"})
//@EnableScheduling
public class AppsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppsysApplication.class, args);
    }
}
