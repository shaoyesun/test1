package com.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestFilter implements Filter {

    private String excludedPages;
    private String[] excludedPageArray;

    public void init(FilterConfig filterConfig) throws ServletException {
        excludedPages = filterConfig.getInitParameter("excludedPages");
        if (!isNull(excludedPages)) {
            excludedPageArray = excludedPages.split(",");
        }
        return;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        System.out.println(((HttpServletRequest) servletRequest).getServletPath());
        boolean isExcludedPage = false;
        for (String page : excludedPageArray) {//判断是否在过滤url之外
            String str = ((HttpServletRequest) servletRequest).getServletPath();
            if(((HttpServletRequest) servletRequest).getServletPath().equals(page)){
                isExcludedPage = true;
                break;
            }
        }

        System.out.println("china");

        if (isExcludedPage) {//在过滤url之外
            filterChain.doFilter(servletRequest, servletResponse);
            //httpResponse.sendRedirect("/test2");
        } else {//不在过滤url之外，判断session是否存在
            httpResponse.sendRedirect("/test2");
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    public void destroy() {

    }

    private boolean isNull(Object obj){
        return obj == null || obj.equals("") ? true : false;
    }

}
