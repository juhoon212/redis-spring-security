package com.example.redissessionseucurity.config;

import com.example.redissessionseucurity.resolver.CustomSessionIdResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.HttpSessionIdResolver;

@Configuration
public class HttpSessionIdResolverConfiguration {

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return new CustomSessionIdResolver("X-AUTH-TOKEN");
    }
}
