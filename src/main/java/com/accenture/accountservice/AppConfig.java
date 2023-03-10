package com.accenture.accountservice;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class AppConfig {

    @Bean
    public Mapper getMapper(){
        return new DozerBeanMapper();
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){ return new RestTemplate(); }
}
