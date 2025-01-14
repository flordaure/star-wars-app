package com.conexaproject.star_wars_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiManager {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
