package com.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class TestFilter implements Filter {

    private String visiter;

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        System.out.println(this.visiter);
        httpResponse.sendRedirect("/test1");
        //filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }

    public String getVisiter() {
        return visiter;
    }

    public void setVisiter(String visiter) {
        this.visiter = visiter;
    }
}
