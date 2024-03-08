package com.example.testapi.common;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CommonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        // 在这里可以处理请求之前的逻辑
        System.out.println("FIlter:在请求之前");

        // 确保请求可以继续传递到下一个filter或目标资源
        filterChain.doFilter(servletRequest, servletResponse);

        // 在这里可以处理请求之后的逻辑
        System.out.println("FIlter:在请求之后");

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
