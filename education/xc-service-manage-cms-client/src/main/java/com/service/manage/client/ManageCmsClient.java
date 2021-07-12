package com.service.manage.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.cms")
@ComponentScan(basePackages={"com.service.manage.client","com.xuecheng.framework"})
public class ManageCmsClient {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsClient.class,args);
    }
}
