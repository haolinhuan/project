package com.spring.shop.springshopeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringShopEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringShopEurekaApplication.class, args);
    }

}
