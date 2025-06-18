package com.ps.assignment.employeeManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import java.util.concurrent.CompletableFuture;

@Component
public class ExternalApiCaller {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${employeeManagement.startupConfigs.thirdPartyApi}")
    private String API_URL;

    @Autowired
    public ExternalApiCaller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "externalApiCallerCircuitBreaker", fallbackMethod = "circuitBreakerFallback")
    public CompletableFuture<String> callApi() {
        return CompletableFuture.supplyAsync(() -> restTemplate.getForObject(API_URL, String.class));
    }

    public CompletableFuture<String> circuitBreakerFallback(Throwable t) {
        return CompletableFuture.completedFuture("External API service is down. Please try again later.");
    }
}