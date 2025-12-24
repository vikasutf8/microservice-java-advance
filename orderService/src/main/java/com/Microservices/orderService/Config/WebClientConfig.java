package com.Microservices.orderService.Config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@Configurable
public class WebClientConfig {
//    The client-side load balancer inside Order Service decides which Inventory instance is called.
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}


/**
 * Without @LoadBalanced →
 * inventory-service will not be resolved
 * */
