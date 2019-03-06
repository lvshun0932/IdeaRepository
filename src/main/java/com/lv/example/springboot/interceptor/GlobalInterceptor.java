package com.lv.example.springboot.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GlobalInterceptor extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private TestInterceptor testInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/swagger-resources/**");
        excludePath.add("/webjars/**");
        excludePath.add("/v2/**");
        excludePath.add("/swagger-ui.html/**");
        excludePath.add("/error/**");
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/**").excludePathPatterns(excludePath);


    }
}
