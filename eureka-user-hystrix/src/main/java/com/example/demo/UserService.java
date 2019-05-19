package com.example.demo;

import java.util.concurrent.Future;

public interface UserService {
    String createOrder(String id);

    Future<String> findOrder(String id);
}
