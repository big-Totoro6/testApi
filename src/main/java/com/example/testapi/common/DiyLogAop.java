package com.example.testapi.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DiyLogAop {
    @Around("@annotation(DiyLog)")
    public Object recordLog(ProceedingJoinPoint point) throws Throwable {
        log.info("entering: " + point + "  args: " + point.getArgs() + "      at: " + point.getSourceLocation());
        Object proceed = point.proceed();
        log.info("执行完毕 执行完毕的结果"+proceed);
        return proceed;
    }
}
