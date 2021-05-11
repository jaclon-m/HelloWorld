package com.jaclon.spring.project.example.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author jaclon
 * @date 2021/1/10
 */
public class MyFIlter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
    }

    @Override
    public void destroy() {

    }
}
