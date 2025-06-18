package com.ps.assignment.employeeManagement.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ps.assignment.employeeManagement.config.ExternalApiCaller;

@ExtendWith(MockitoExtension.class)
public class ResilentApiControllerTest {

    @Mock
    private ExternalApiCaller externalApiCaller;

    @InjectMocks
    private ResilentApiController resilentApiController;

    @BeforeEach
    public void setUp() throws Exception {
        resilentApiController = new ResilentApiController();
		java.lang.reflect.Field field = ResilentApiController.class.getDeclaredField("externalApiCaller");
		field.setAccessible(true);
		field.set(resilentApiController, externalApiCaller);
    }

    @Test
    public void testCircuitBreaker() {
        when(externalApiCaller.callApi()).thenReturn(CompletableFuture.completedFuture("API response"));

        String response = resilentApiController.circuitBreaker().join();
        assertEquals("API response", response);

        verify(externalApiCaller, times(1)).callApi();
    }

    @Test
    public void testFallbackAfterCircuitBreaker() {
        String response = resilentApiController.fallbackAfterCircuitBreaker(new Exception("Service unavailable"));
        assertEquals("External service is not available...", response);
    }

    @Test
    public void testRetryApi() {
        when(externalApiCaller.callApi()).thenReturn(CompletableFuture.completedFuture("API response"));

        String response = resilentApiController.retryApi();
        assertEquals("API response", response);

        verify(externalApiCaller, times(1)).callApi();
    }

    @Test
    public void testFallbackAfterRetry() {
        String response = resilentApiController.fallbackAfterRetry(new Exception("All retries exhausted"));
        assertEquals("all retries have exhausted", response);
    }

    @Test
    public void testTimeLimiterApi() {
        when(externalApiCaller.callApi()).thenReturn(CompletableFuture.completedFuture("API response"));

        String response = resilentApiController.timeLimiterApi().join();
        assertEquals("API response", response);

        verify(externalApiCaller, times(1)).callApi();
    }

    @Test
    public void testFallbackAfterTimeLimiter() {
        String response = resilentApiController.fallbackAfterTimeLimiter(new Exception("Time limit exceeded"));
        assertEquals("Maximun time limit exceeded for external api", response);
    }
}