package com.example.demo;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderCollapseCommand extends HystrixCollapser<String, String, String> {

    private UserService userService;
    private String userId;

    public CreateOrderCollapseCommand(UserService userService, String userId) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("CreateOrderCollapseCommand"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100)));
        this.userService = userService;
        this.userId = userId;
    }

    @Override
    public String getRequestArgument() {
        return this.userId;
    }

    @Override
    protected HystrixCommand<String> createCommand(Collection<CollapsedRequest<String, String>> collapsedRequests) {
        List<String> userIds = collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList());
        return new CreateOrderBatchCommand(userService, userIds);
    }

    @Override
    protected void mapResponseToRequests(String batchResponse, Collection<CollapsedRequest<String, String>> collapsedRequests) {
        for (CollapsedRequest<String, String> collapsedRequest : collapsedRequests) {
            collapsedRequest.setResponse(batchResponse);
        }
    }
}
