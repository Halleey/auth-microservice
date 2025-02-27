package com.api.auth.config;

import com.api.auth.jwt.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return  WebClient.builder().baseUrl("htpp://localhost:8080").build();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return  new JwtUtil();
    }


}
