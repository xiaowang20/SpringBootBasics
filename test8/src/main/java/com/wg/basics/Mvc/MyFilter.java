package com.wg.basics.Mvc;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *  程序启动时，调用init()
 *
 */
@WebFilter(value = "/")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
chain.doFilter(request, response);
        System.out.println("doFilter");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
