package com.funtl.itoken.service.sso.config;

import com.funtl.itoken.service.sso.interceptor.ConstantsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截所有  addPathPatterns("/**")
         */
        registry.addInterceptor(new ConstantsInterceptor()).addPathPatterns("/**");
    }
}
