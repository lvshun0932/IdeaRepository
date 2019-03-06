package com.lv.example.springboot.interceptor;

import com.alibaba.fastjson.JSON;
import com.lv.example.springboot.annotation.AuthIgnore;
import com.lv.example.springboot.constant.Constants;
import com.lv.example.springboot.entity.AuthEntity;
import com.lv.example.springboot.entity.UserEntity;
import com.lv.example.springboot.exception.AuthException;
import com.lv.example.springboot.manager.ITokenManager;
import com.lv.example.springboot.util.HttpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {


    private static final Logger loger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Autowired
    private ITokenManager authManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        loger.info("拦截:url = " + request.getRequestURL().toString());
        AuthIgnore annotation;
        if(handler instanceof HandlerMethod)
            annotation =((HandlerMethod)handler).getMethodAnnotation(AuthIgnore.class);
        else
            return true;

        if(annotation != null)
            return true;

        String token = request.getHeader(Constants.TOKEN);
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(Constants.TOKEN);
        }
        if(StringUtils.isEmpty(token)){
            Object obj = request.getAttribute(Constants.TOKEN);
            if(null != obj)
                token = obj.toString();
        }

        loger.info("request内容 = " + HttpHelper.getBodyString(request));
        if(StringUtils.isEmpty(token)){
            AuthEntity entity = JSON.parseObject(HttpHelper.getBodyString(request), AuthEntity.class);
            if(entity != null)
                token = entity.getToken();
        }



        if(StringUtils.isEmpty(token)){
            throw new AuthException(444,"崩了");
        } else {
            loger.info("token = " + token);
            UserEntity userEntity = authManager.getUserInfoByToken(token);
            if(userEntity == null)
                throw new AuthException(555,"token验证失败");
        }


        return true;
    }
}
