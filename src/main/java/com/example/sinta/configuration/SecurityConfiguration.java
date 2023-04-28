package com.example.sinta.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security
                .csrf().disable() // DON'T DO THIS IN PRODUCTION, YOU SHOULD AWARE WITH CSRF ATTACK
                .httpBasic().disable()
                .formLogin().disable()
                .cors(c -> {
                    CorsConfigurationSource src = (r) -> {
                        CorsConfiguration config = new CorsConfiguration();
                        config.addAllowedHeader("*");
                        config.addAllowedMethod("*");
                        config.addAllowedOrigin("*");
                        return config;
                    };
                    c.configurationSource(src);
                })
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll()
                .and()
                .build();
    }
}
