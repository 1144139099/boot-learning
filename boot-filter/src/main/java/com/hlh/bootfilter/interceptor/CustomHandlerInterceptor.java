package com.hlh.bootfilter.interceptor;

import com.hlh.bootfilter.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
public class CustomHandlerInterceptor implements HandlerInterceptor {
    @Resource
    private TestService testService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        log.info("preHandle:请求前调用");
        log.info(testService.test());
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        log.info("postHandle:请求后调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        log.info("afterCompletion:请求调用完成后回调方法，即在试图渲染完成后回调");
    }
}
