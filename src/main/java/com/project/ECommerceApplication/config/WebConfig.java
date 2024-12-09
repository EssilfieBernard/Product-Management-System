package com.project.ECommerceApplication.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class WebConfig {

    @Bean
    public ServletRegistrationBean<DispatcherServlet> dispatcherServletServletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<>(dispatcherServlet, "/api");
        registration.setName("customDispatcher");
        return registration;
    }
}
