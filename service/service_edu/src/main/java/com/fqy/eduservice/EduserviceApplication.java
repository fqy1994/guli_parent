package com.fqy.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author fan_jennifer
 * @create 2021-08-2021/8/7 23:11
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.fqy"})
public class EduserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduserviceApplication.class,args);

    }
}
