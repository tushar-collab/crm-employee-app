package com.ps.assignment.employeeManagement.aop;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopUtils {

    private static final Logger LOG = LogManager.getLogger(AopUtils.class);

    @Pointcut("execution(* com.ps.assignment.employeeManagement.service.*.*(..))")
    public void servicePointcut() {
    }

    @Around("servicePointcut()")
    public Object aroundServicePointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object retVal = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("Execution of ").append(className).append(".").append(methodName).append(" took ")
                .append(endTime - startTime).append(" ms");
        if (retVal instanceof Collection) {
            logMessage.append(" Size of the collection returned is ").append(((Collection<?>) retVal).size());
        }
        LOG.info(logMessage.toString());
        return retVal;
    }

    @AfterThrowing(pointcut = "servicePointcut()", throwing = "error")
    public void afterThrowingServicePointcut(JoinPoint joinPoint, Throwable error) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        LOG.error("Exception in " + className + "." + methodName + " :: " + error.getMessage());
    }

}
