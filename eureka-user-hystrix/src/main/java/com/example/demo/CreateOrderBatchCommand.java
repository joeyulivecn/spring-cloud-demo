package com.example.demo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

import java.util.List;

public class CreateOrderBatchCommand extends HystrixCommand<String> {

    private UserService userService;
    private List<String> userIds;

    public CreateOrderBatchCommand(UserService userService, List<String> userIds) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CreateOrderGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CreateOrderBatchCommand")));
        this.userService = userService;
        this.userIds = userIds;
    }

    @Override
    protected String run() throws Exception {
        return userService.createOrder(userIds.get(0));
    }
}
