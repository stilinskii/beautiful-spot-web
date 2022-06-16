package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.Filter;


@Configuration
public class Config {

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(2000000000);
        return multipartResolver;
    }

    @Bean
    public FilterRegistrationBean springMultipartFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(springMultipartFilter());
        registration.addUrlPatterns("/*");
        registration.setName("springMultipartFilter");
        registration.setOrder(1);
        return registration;
    }
    @Bean
    public Filter springMultipartFilter() {
        return new MultipartFilter();
    }

//    @Bean
//    public FilterRegistrationBean springSecurityFilterChainRegistration() {
//
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(springSecurityFilterChain());
//        registration.addUrlPatterns("/*");
//        registration.setName("springMultipartFilter");
//        registration.setOrder(2);
//        return registration;
//    }
//    @Bean
//    public Filter springSecurityFilterChain() {
//        return new DelegatingFilterProxy();
//    }



}
