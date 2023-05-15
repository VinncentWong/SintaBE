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
        if(claims.get("role").equals(Role.AGEN_TRAVEL.name())){
            AgenTravel agenTravel = new AgenTravel();
            agenTravel.setId(((Integer)claims.get("id")).longValue());
            agenTravel.setNama((String)claims.get("nama"));
            agenTravel.setCreatedAt(new Date(((Long)claims.get("createdAt")).longValue()));
            this.data = agenTravel;
        } else {
            User user = new User();
            user.setId(((Integer)claims.get("id")).longValue());
            user.setNama((String)claims.get("nama"));
            user.setCreatedAt(new Date(((Long)claims.get("createdAt")).longValue()));
            this.data = user;
        }
    }    
    
}
