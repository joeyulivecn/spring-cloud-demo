package com.example.demo;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/{id}/order")
    @HystrixCommand(fallbackMethod = "fallbackInfo")
    public String findOrderByUserId(@PathVariable String id) {
        return this.restTemplate.getForObject("http://eureka-order/order/1", String.class);
    }

    public String fallbackInfo(@PathVariable String id) {
        return "Service is unavailable, please try again later";
    }
}
