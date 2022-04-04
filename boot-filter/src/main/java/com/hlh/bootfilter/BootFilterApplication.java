package com.hlh.bootfilter;

import com.hlh.bootfilter.listener.MyListener1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@ServletComponentScan
@SpringBootApplication
public class BootFilterApplication {

    public static void main(String[] args) {
        //获取ConfigurableApplicationContext上下文
        ConfigurableApplicationContext context = SpringApplication.run(BootFilterApplication.class, args);
        //装载监听
        context.addApplicationListener(new MyListener1());
    }

}
