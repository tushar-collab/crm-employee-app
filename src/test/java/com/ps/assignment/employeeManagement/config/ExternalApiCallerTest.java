package com.ps.assignment.employeeManagement.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class ExternalApiCallerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ExternalApiCaller externalApiCaller;

    @Value("${employeeManagement.startupConfigs.thirdPartyApi}")
    private String API_URL;

    @BeforeEach
    public void setUp() {
        externalApiCaller = new ExternalApiCaller(restTemplate);
		try {
			java.lang.reflect.Field apiUrlField = ExternalApiCaller.class.getDeclaredField("API_URL");
			apiUrlField.setAccessible(true);
			apiUrlField.set(externalApiCaller, API_URL);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			fail("Failed to set API_URL via reflection: " + e.getMessage());
		}
    }

    @Test
    public void testCallApi() {
        String expectedResponse = "API response";
		try {
			java.lang.reflect.Field apiUrlField = ExternalApiCaller.class.getDeclaredField("API_URL");
			apiUrlField.setAccessible(true);
			apiUrlField.set(externalApiCaller, API_URL);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			fail("Failed to set API_URL via reflection: " + e.getMessage());
		}
		when(restTemplate.getForObject(API_URL, String.class)).thenReturn(expectedResponse);

		String actualResponse = null;
		try {
			actualResponse = externalApiCaller.callApi().get();
		} catch (InterruptedException | ExecutionException e) {
			fail("Failed to call API: " + e.getMessage());
		}
		assertEquals(expectedResponse, actualResponse);

		try {
			java.lang.reflect.Field apiUrlField = ExternalApiCaller.class.getDeclaredField("API_URL");
			apiUrlField.setAccessible(true);
			verify(restTemplate, times(1)).getForObject((String) apiUrlField.get(externalApiCaller), String.class);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			fail("Failed to verify API_URL via reflection: " + e.getMessage());
		}
    }
}