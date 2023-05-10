package com.example.sinta.security.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.example.sinta.security.provider.JwtProvider;

public class JwtManager implements AuthenticationManager{
    
    private final JwtProvider provider;

    public JwtManager(JwtProvider provider) {
        this.provider = provider;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return this.provider.authenticate(authentication);
    }
}
