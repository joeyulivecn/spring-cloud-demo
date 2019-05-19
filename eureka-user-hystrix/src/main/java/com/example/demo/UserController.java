package com.example.demo;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

//    @GetMapping("/user/{id}/order")
//    @HystrixCommand(fallbackMethod = "fallbackInfo")
//    public String findOrderByUserId(@PathVariable String id) {
//        return this.restTemplate.getForObject("http://eureka-order/order/1", String.class);
//    }
//
//    public String fallbackInfo(@PathVariable String id) {
//        return "Service is unavailable, please try again later";
//    }

    @PostMapping("/user/{id}/order")
    public String createOrder(@PathVariable String id) {
        log.error("create order for user {}.", id);
        return userService.createOrder(id);
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) throws ExecutionException, InterruptedException {
        log.error("find order {}.", id);
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> f1 = userService.findOrder("1");
        Future<String> f2 = userService.findOrder("2");
        Future<String> f3 = userService.findOrder("3");

        log.error(f1.get());
        log.error(f2.get());
        log.error(f3.get());
        context.close();
        return null;
    }
}
