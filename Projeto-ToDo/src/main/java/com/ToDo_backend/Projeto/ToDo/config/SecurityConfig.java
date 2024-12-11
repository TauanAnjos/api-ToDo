package com.ToDo_backend.Projeto.ToDo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/user").permitAll()
                        .requestMatchers("/h2/**").permitAll()
                        .anyRequest().permitAll() // Permitir acesso a todas as solicitações
                )
                .csrf(csrf -> csrf.disable()) // Desativar CSRF
                .headers(headers -> headers.frameOptions().sameOrigin());

        return http.build();
    }
}
