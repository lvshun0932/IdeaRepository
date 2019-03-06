package com.lv.example.springboot.interceptor;

import com.lv.example.springboot.util.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger loger = LoggerFactory.getLogger(TestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String body =  HttpHelper.getBodyString(request);
        loger.info("interceptor request body = " + body);
        String body2 =  HttpHelper.getBodyString(request);
        loger.info("interceptor request body2 = " + body2);

        return true;
    }

    }
