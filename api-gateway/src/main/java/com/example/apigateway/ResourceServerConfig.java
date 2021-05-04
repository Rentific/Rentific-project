package com.example.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(null);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and().
                authorizeRequests()
                .antMatchers(HttpMethod.POST, "/real-estate/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE, "/real-estate/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT, "/real-estate/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/real-estate/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/invoice/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/rent/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers(HttpMethod.POST, "/users/user/add").permitAll()
                .antMatchers("/users/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers("/**").permitAll();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
   }


