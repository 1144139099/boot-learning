package com.hlh.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;



@SpringBootApplication
@ImportResource(value = {"classpath:beans.xml"})
public class ConfigApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConfigApplication.class, args);
    }

}