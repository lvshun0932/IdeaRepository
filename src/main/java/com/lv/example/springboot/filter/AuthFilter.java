package com.lv.example.springboot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class AuthFilter implements Filter {

    private static final Logger loger = LoggerFactory.getLogger(AuthFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        BodyReaderHttpServletRequestWrapper wrapper = null;
        if(servletRequest instanceof HttpServletRequest){
            loger.info("过滤器");
            wrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        }

        if(wrapper == null)
            filterChain.doFilter(servletRequest,servletResponse);
        else
            filterChain.doFilter(wrapper,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
