package com.ps.assignment.employeeManagement.aop;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AopUtilsTest {

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    private MethodSignature methodSignature;

    @InjectMocks
    private AopUtils aopUtils;

    @BeforeEach
    public void setUp() {
        when(proceedingJoinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getName()).thenReturn("testMethod");
        when(proceedingJoinPoint.getTarget()).thenReturn(new Object());
    }

    @Test
    public void testAroundServicePointcut() throws Throwable {
        List<String> mockList = new ArrayList<>();
        mockList.add("test");

        when(proceedingJoinPoint.proceed()).thenReturn(mockList);

        Object result = aopUtils.aroundServicePointcut(proceedingJoinPoint);
        verify(proceedingJoinPoint, times(1)).proceed();
        assert result instanceof List;
        assert ((List<?>) result).size() == 1;
    }

    @Test
    public void testAfterThrowingServicePointcut() {
        Throwable throwable = new RuntimeException("Test Exception");

        aopUtils.afterThrowingServicePointcut(proceedingJoinPoint, throwable);
        verify(proceedingJoinPoint, times(1)).getSignature();
        verify(proceedingJoinPoint, times(1)).getTarget();
    }
}