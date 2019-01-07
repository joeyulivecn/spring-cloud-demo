package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringCloudCfgClientApplication {

    @Value("${clientParam}")
    private String clientParam;

    @GetMapping("/client-param")
    public String getClientParam() {
        return this.clientParam;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudCfgClientApplication.class, args);
    }

}

