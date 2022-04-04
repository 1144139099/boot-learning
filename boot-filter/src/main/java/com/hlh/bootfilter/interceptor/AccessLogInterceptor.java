package com.hlh.bootfilter.interceptor;

import com.hlh.bootfilter.domain.AccessLog;
import com.hlh.bootfilter.util.AddressIpUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@Component
public class AccessLogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception{
        //创建日志实体
        AccessLog accessLog = new AccessLog();
        //设置lp地址
        accessLog.setIp(request.getRemoteAddr());
        //设置请求方法，GET,POST
        accessLog.setHttpMethod(request.getMethod());
        //设置请求路径
        accessLog.setUrl(request.getRequestURI());
        //设置请求开始时间
        request.setAttribute("sendTime", System.currentTimeMillis());
        //设置请求实体到request内，方便afterCompletion方法调用
        request.setAttribute("accessLog", accessLog);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws  Exception{
        //获取本次请求日志实体
        AccessLog accessLog = (AccessLog) request.getAttribute("accessLog");
        //获取请求错误码，根据需求存入数据库，这里不保存
        int status = response.getStatus();
        accessLog.setHttpStatus(status);
        //设置访问者
        accessLog.setUsername(request.getParameter("username"));
        //当前时间
        long currentTime = System.currentTimeMillis();
        //请求开始时间
        long sendTime = Long.parseLong(request.getAttribute("sendTime").toString());
        //设置请求时间差
        accessLog.setDuration(Integer.valueOf((currentTime - sendTime) + ""));
        accessLog.setCreateTime(new Date());
        log.info(accessLog.toString());
    }
}
