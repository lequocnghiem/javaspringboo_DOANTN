package com.api.thuctaptotnghiepbackend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class ShippingConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
