package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    public String createOrder(String id) {
        log.error("create order for user {}.", id);
        return "order created: " + LocalDateTime.now().toString();
    }

    @Override
    @HystrixCollapser(batchMethod = "findOrders", collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "100")}, scope = com.netflix.hystrix.HystrixCollapser.Scope.REQUEST)
    public Future<String> findOrder(String id) {
        throw new RuntimeException("This method body should not be executed");
    }

    @HystrixCommand
    public List<String> findOrders(List<String> ids) {
        List<String> orders = new ArrayList<>();

        String order = "order: " + ids.get(0) + LocalDateTime.now().toString();

        for (String id : ids) {
            orders.add(order);
        }

        return orders;
    }
}
