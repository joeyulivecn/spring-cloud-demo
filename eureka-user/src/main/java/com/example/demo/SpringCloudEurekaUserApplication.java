package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class SpringCloudEurekaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaUserApplication.class, args);
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/{userId}/order")
    public String findOrderByUserId(@PathVariable String userId) {
        return this.restTemplate.getForObject("http://eureka-order/order/1", String.class);
    }

    @GetMapping("/hello")
    public String home() {
        return "hello world";
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

