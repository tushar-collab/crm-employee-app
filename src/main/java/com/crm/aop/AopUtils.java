package com.crm.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.crm.service.impl.EmployeeServiceImpl;

@Component
@Aspect
public class AopUtils {

    private static final Logger LOG = LogManager.getLogger(EmployeeServiceImpl.class);

    @Pointcut("execution(* com.crm.service.impl.*.*(..))")
    public void serviceLayerExecution() {
    }

    @Around("serviceLayerExecution()")
    public Object logServiceExecution(org.aspectj.lang.ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        LOG.info("Entering method: {}", methodName);
        long startTime = System.currentTimeMillis();

        Object result;
        result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        LOG.info("Exiting method: {} - Execution time: {} ms", methodName, (endTime - startTime));
        return result;
    }

    @AfterThrowing(pointcut = "serviceLayerExecution()", throwing = "exception")
    public void logServiceException(org.aspectj.lang.JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        LOG.error("Exception in method: {} - Exception message: {}", methodName, exception.getMessage());
    }
}
