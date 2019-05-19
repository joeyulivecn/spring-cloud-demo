package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableEurekaClient
@EnableCircuitBreaker
//@EnableHystrixDashboard
@SpringBootApplication
public class SpringCloudEurekaUserHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaUserHystrixApplication.class, args);
    }

    @Bean
//    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

