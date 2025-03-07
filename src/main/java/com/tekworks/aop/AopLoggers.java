package com.tekworks.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class AopLoggers {

    @Before("execution(* com.tekworks.service..*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("Before executing method: {} with arguments: {}", 
                 joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @After("execution(* com.tekworks.service..*(..))")
    public void logAfterMethod(JoinPoint joinPoint) {
        log.info("After executing method: {}", joinPoint.getSignature().getName());
    }

    @Around("execution(* com.tekworks.service..*(..))")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Before executing method: {}", joinPoint.getSignature().getName());
        
        Object result = joinPoint.proceed();
        
        log.info("After executing method: {}", joinPoint.getSignature().getName());
        return result;
    }

    @AfterThrowing(pointcut = "execution(* com.tekworks.service..*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error("Exception in method: {} with message: {}", 
                  joinPoint.getSignature().getName(), ex.getMessage(), ex);
    }
}
