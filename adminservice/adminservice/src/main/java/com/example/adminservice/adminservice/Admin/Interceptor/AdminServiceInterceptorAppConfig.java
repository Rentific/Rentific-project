package com.example.adminservice.adminservice.Admin.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;
        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class AdminServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    AdminServiceInterceptor adminServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminServiceInterceptor);
    }
}