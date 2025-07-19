package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    private Logger logger =Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}

    @Before("forAppFlow()")
        public void before(JoinPoint joinPoint){

        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("=====>>>>> in @Before: calling Method: " + theMethod);

        Object[] args = joinPoint.getArgs();

        for(Object tempArg : args){
            logger.info("Args : ====>>>>>>"+ tempArg);
        }

    }

        @AfterReturning(pointcut = "forAppFlow()",returning = "theResult")
        public  void afterReturning(JoinPoint joinPoint,Object theResult){
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("The Method : =======>" + theMethod);
        logger.info("The result =======>>>>" + theResult);
        }
    }
