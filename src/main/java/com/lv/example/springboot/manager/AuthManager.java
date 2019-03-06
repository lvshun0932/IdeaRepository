package com.lv.example.springboot.manager;

import com.lv.example.springboot.constant.Constants;
import com.lv.example.springboot.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthManager {
    @Autowired
    private ITokenManager tokenManager;


    private HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public UserEntity getUserInfo(){
        HttpServletRequest request = getRequest();
        String token = request.getAttribute(Constants.TOKEN).toString();
        return tokenManager.getUserInfoByToken(token);
    }

}
