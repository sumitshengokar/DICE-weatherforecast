package com.example.dice.weatherforecast.config;

import com.example.dice.weatherforecast.security.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    public FilterRegistrationBean<AuthenticationFilter> authenticationFilterFilterRegistrationBean(){
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns("/weather/*");
        return registrationBean;
    }
}
