package com.example.sinta.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.sinta.security.authentication.JwtAuthentication;
import com.example.sinta.util.JwtUtil;

import io.jsonwebtoken.Jwts;

public class JwtProvider implements AuthenticationProvider{

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object jwtToken = authentication.getPrincipal() ;
        try{
            var parser = Jwts
                    .parserBuilder()
                    .setSigningKey(JwtUtil.INSTANCE.getKey())
                    .build();
            var jws = parser.parseClaimsJws((String)jwtToken);
            List<GrantedAuthority> auth = new ArrayList<>();
            auth.add(new SimpleGrantedAuthority("ROLE_" + (String)jws.getBody().get("role")));
            var jwtAuth = new JwtAuthentication(jwtToken, null, auth);
            jwtAuth.setData(jws.getBody());
            return jwtAuth;
        } catch(Exception e){
            return new JwtAuthentication(e.getMessage(), null);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        if(authentication.equals(JwtAuthentication.class)){
            return true;
        } else {
            return false;
        }
    }
    
}
