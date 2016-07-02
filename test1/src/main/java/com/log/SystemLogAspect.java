package com.log;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class SystemLogAspect extends HandlerInterceptorAdapter{

	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    	System.out.println("hander = "+handler);
        if (!(handler instanceof HandlerMethod)) {
            return;
        }

        
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        final Log log = method.getAnnotation(Log.class);
        System.out.println("输出日志描述:"+log.desc());
        if (log != null) {
        	String desc = log.desc();
            boolean view = log.view();
            String opeDesc = log.operationDesc();
            if (view) {
                long endTime = System.currentTimeMillis();
                long beginTime = startTimeThreadLocal.get();
                long consumeTime = endTime - beginTime;
                System.out.println("view = " + view + " : opeDesc = "+opeDesc+" : beginTime = "+beginTime + "endTime = " + endTime + " consumeTime = " + consumeTime);
            }
        }
    }
}
