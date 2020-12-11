package com.example.springbuckdemo.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class PerformanceAspect {
  @Around("repositoryOps()")
  public Object logPerformance(ProceedingJoinPoint pjp) throws Throwable{
    long startTime = System.currentTimeMillis();
    String name = "-";
    String result = "Y";
    try{
      name = pjp.getSignature().toShortString();
      return pjp.proceed();
    }
    catch(Throwable t){
      result = "N";
      throw  t;
    }finally {
      long endTime = System.currentTimeMillis();
      log.info("{};{};{}ms",name, result, endTime - startTime);
    }
  }

  @Pointcut("execution(* com.example..springbuckdemo.repository..*(..))")
  private void repositoryOps(){}

}