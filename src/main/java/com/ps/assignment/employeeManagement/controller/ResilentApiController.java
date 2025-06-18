package com.ps.assignment.employeeManagement.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.assignment.employeeManagement.config.ExternalApiCaller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@Tag(name = "Resilence operations", description = "Resilence operations")
public class ResilentApiController {

    @Autowired
    private ExternalApiCaller externalApiCaller;

    @GetMapping("/circuit-breaker")
    @CircuitBreaker(name = "CircuitBreakerService", fallbackMethod = "fallbackAfterCircuitBreaker")
    @Operation(summary = "Circuit breaker", description = "Circuit breaker API", tags = { "Resilence operations" })
    @ApiResponse(responseCode = "201", description = "Circuit state")
    public CompletableFuture<String> circuitBreaker() {
        return CompletableFuture.supplyAsync(() -> externalApiCaller.callApi()).thenCompose(future -> future);
    }

    public String fallbackAfterCircuitBreaker(Exception ex) {
        return "External service is not available...";
    }

    @GetMapping("/retry")
    @Operation(summary = "Retry", description = "Retry API", tags = { "Resilence operations" })
    @ApiResponse(responseCode = "201", description = "Retry API")
    @Retry(name = "retryApi", fallbackMethod = "fallbackAfterRetry")
    public String retryApi() {
        return externalApiCaller.callApi().join();
    }

    public String fallbackAfterRetry(Exception ex) {
        return "all retries have exhausted";
    }

    @TimeLimiter(name = "apiLoaderLimiter", fallbackMethod = "fallbackAfterTimeLimiter")
    public CompletableFuture<String> timeLimiterApi() {
        return CompletableFuture.supplyAsync(() -> externalApiCaller.callApi()).thenCompose(future -> future);
    }

    public String fallbackAfterTimeLimiter(Exception ex) {
        return "Maximun time limit exceeded for external api";
    }
}
