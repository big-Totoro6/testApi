package com.example.testapi.config;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
public class TraceAopConfig {

    /**
     * 做一个切面
     */
    @Pointcut("execution(* com.example.testapi.controller..*.*(..))")
    public void monitor(){};

    /**
     * 监控追踪
     * @return
     */
    @Bean
    public Advisor traceAdvisor(){
        //使用定制化的跟踪拦截器
        CustomizableTraceInterceptor customizableTraceInterceptor=new CustomizableTraceInterceptor();
        customizableTraceInterceptor.setEnterMessage("Entering method '$[methodName]' of class [$[targetClassName]] arguments:  $[arguments]");
        customizableTraceInterceptor.setExitMessage("Exiting method '$[methodName]' of class [$[targetClassName]]  arguments :$[arguments]");
        //使用动态日志
        customizableTraceInterceptor.setUseDynamicLogger(true);
        //自定义的切点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("com.example.testapi.config.TraceAopConfig.monitor()");
        //在ponitcut切面上 实现自定义的功能 （配置切面增强）
        return new DefaultPointcutAdvisor(pointcut,customizableTraceInterceptor);
    }
}
