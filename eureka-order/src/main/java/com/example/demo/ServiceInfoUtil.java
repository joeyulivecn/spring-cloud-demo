package com.example.demo;

import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceInfoUtil implements ApplicationListener<ServletWebServerInitializedEvent> {
    private static ServletWebServerInitializedEvent event;

    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        ServiceInfoUtil.event = event;
    }

    public static int getPort() {
        int port = event.getApplicationContext().getWebServer().getPort();
        return port;
    }
}
