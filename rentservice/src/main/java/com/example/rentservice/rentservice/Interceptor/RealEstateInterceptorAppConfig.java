package com.example.rentservice.rentservice.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class RealEstateInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    RealEstateInterceptor realEstateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(realEstateInterceptor);
    }
}
