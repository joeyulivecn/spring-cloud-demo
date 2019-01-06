package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class SpringCloudEurekaOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaOrderApplication.class, args);
    }


    @GetMapping("/order/{id}")
    public String findOrderById(@PathVariable String id) {
        System.out.println("---------> " + ServiceInfoUtil.getPort());
        return "Order 1";
    }
}

