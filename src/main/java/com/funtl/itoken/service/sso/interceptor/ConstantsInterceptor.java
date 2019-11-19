package com.funtl.itoken.service.sso.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConstantsInterceptor implements HandlerInterceptor {
    private static final String HOST_CDN = "106.75.12.119:9001";
    private static  final String TEMPLATE_ADMIN_LTE = "/adminlte/v2.4.3/AdminLTE-2.4.3";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        if(modelAndView!=null){
            modelAndView.addObject("adminlte",HOST_CDN+TEMPLATE_ADMIN_LTE);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
