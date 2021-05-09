package com.example.invoiceservice.invoiceservice.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Component;
        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InvoiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    InvoiceInterceptor invInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(invInterceptor);
    }
}
