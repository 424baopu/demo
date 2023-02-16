package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.demo.*"})
public class DemoApplication {

    public static void main(String[] args) {
        // 返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
        // 查看组件
        String[] strings = run.getBeanDefinitionNames();
        for (String name : strings) {
            System.out.println(name);
        }
    }

}
