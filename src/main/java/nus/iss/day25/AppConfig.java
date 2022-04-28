package nus.iss.day25;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nus.iss.day25.filters.LoginFilter;

@Configuration
public class AppConfig {
    
    @Bean
    public FilterRegistrationBean<LoginFilter> registerFilter(){
        //Create an instance of authentication filter
        LoginFilter loginFilter = new LoginFilter();

        // Create an instance of registration filter
        FilterRegistrationBean<LoginFilter> regFilter = new FilterRegistrationBean<>();
        regFilter.setFilter(loginFilter);
        regFilter.addUrlPatterns("/protected/*");

        return regFilter;
    }
}
