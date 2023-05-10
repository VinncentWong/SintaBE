package com.example.sinta.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.example.sinta.exception.JwtTokenInvalidException;
import com.example.sinta.security.authentication.JwtAuthentication;
import com.example.sinta.security.manager.JwtManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    private final JwtManager manager;


    public JwtFilter(JwtManager manager) {
        this.manager = manager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }
        if(!token.startsWith("Bearer ")){
            throw new RuntimeException("apakah token yang dimasukkan adalah jwt token?");
        } else {
            String filteredToken = token.substring(7);
            Authentication auth = this.manager.authenticate(new JwtAuthentication(filteredToken, null));
            if(auth.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(auth);
                filterChain.doFilter(request, response);
            } else {
                this.exceptionResolver.resolveException(request, response, null, new JwtTokenInvalidException("token jwt invalid, pesan: " + auth.getPrincipal().toString()));            }
        }
    }
    
}
