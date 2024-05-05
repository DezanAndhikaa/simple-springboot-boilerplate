package com.example.demo.controller.middleware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
    private final JwtAuthFilter jwtAuthenticationFilter;

    public SecurityConfiguration(JwtAuthFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).
                authorizeHttpRequests(
                        auth -> auth.requestMatchers("/api/v1/user/login", "/api/v1/user/register", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                ).
                authorizeHttpRequests(
                        auth -> auth.requestMatchers("/api/v1/user/me").authenticated()
                ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
