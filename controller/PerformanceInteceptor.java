package com.example.springbuckdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class PerformanceInteceptor implements HandlerInterceptor {
  private ThreadLocal<StopWatch> stopWatch = new ThreadLocal<>();

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    StopWatch sw = new StopWatch();
    stopWatch.set(sw);
    sw.start();
    return true;
  }


  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    stopWatch.get().stop();//在任务完成之后停止
    stopWatch.get().start(); // 记录postHandle到afterCompletion这段时间
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    StopWatch sw = stopWatch.get();
    sw.stop();
    String method = handler.getClass().getSimpleName();
    if(handler instanceof HandlerMethod){
      String beanType = ((HandlerMethod) handler).getBeanType().getName();
      String methodName = ((HandlerMethod) handler).getMethod().getName();
    }

    // 获得的是哪个URI的请求，使用的是哪个handler的哪个方法做的处理，获得HttpStatus，如果有异常将异常类名打出，
    // 用总耗时减去上一个节点的记录时间表示prehandle 到 posthandle的时间
    // posthandle 到 afterCompletion之间的耗时
    log.info("{};{};{};{};{};{}ms;{}ms;{}ms",
      request.getRequestURI(),
      method,
      response.getStatus(),
      ex == null ? "-" : ex.getClass().getSimpleName(),
      sw.getTotalTimeMillis(),
      sw.getTotalTimeMillis() - sw.getLastTaskTimeMillis(),
      sw.getLastTaskTimeMillis());
    stopWatch.remove();
  }
}
