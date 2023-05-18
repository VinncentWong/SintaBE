package com.example.sinta.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.example.sinta.security.filter.JwtFilter;
import com.example.sinta.security.manager.JwtManager;
import com.example.sinta.security.provider.JwtProvider;

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
                        config.setAllowedHeaders(List.of("*"));
                        config.setAllowedMethods(List.of("*"));
                        config.setAllowedOrigins(List.of("*"));
                        return config;
                    };
                    c.configurationSource(src);
                })
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/user/create", "/user/login", "/user/update/verifikasi/**", "/user/get/**")
                .permitAll()
                .requestMatchers("/agentravel/create", "/agentravel/login", "/agentravel/update/verifikasi/**", "/agentravel/get/**")
                .permitAll()
                .requestMatchers("/premium/get/**").permitAll()
                .requestMatchers("/bank/get/**").permitAll()
                .requestMatchers("/paketwisata/get/**").permitAll()
                .requestMatchers("/portofolio/get/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .build();
    }

    @Bean
    public JwtProvider jwtProvider(){
        return new JwtProvider();
    }

    @Bean
    public JwtManager jwtManager(){
        return new JwtManager(jwtProvider());
    }

    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter(jwtManager());
    }
}
