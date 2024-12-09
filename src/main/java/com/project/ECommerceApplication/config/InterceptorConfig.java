package com.project.ECommerceApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private BasicAuthenticationInterceptor basicAuthenticationInterceptor;

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basicAuthenticationInterceptor)
                .addPathPatterns("/api/products/**", "/api/reviews/**");
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/api/products/**", "/api/reviews/**");

    }

}
