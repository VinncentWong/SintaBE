package com.example.sinta.security.authentication;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.example.sinta.domain.AgenTravel;
import com.example.sinta.domain.Extractable;
import com.example.sinta.domain.Role;
import com.example.sinta.domain.User;

import io.jsonwebtoken.Claims;

public class JwtAuthentication extends UsernamePasswordAuthenticationToken{

    private Extractable data;

    public JwtAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }    

    public JwtAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public Extractable getData() {
        return data;
    }

    public void setData(Claims claims) {
        if(claims.get("role").equals(Role.AGEN_TRAVEL)){
            AgenTravel agenTravel = new AgenTravel();
            agenTravel.setId((Long)claims.get("id"));
            agenTravel.setNama((String)claims.get("nama"));
            agenTravel.setCreatedAt((Date)claims.get("createdAt"));
            this.data = agenTravel;
        } else {
            User user = new User();
            user.setId((Long)claims.get("id"));
            user.setNama((String)claims.get("nama"));
            user.setCreatedAt((Date)claims.get("createdAt"));
            this.data = user;
        }
    }    
    
}
