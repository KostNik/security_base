package com.oreilly.security.token;

import com.oreilly.security.domain.entities.AutoUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthToken extends UsernamePasswordAuthenticationToken {


    private String make;

    public CustomAuthToken(String principal, String credentials, String make) {
        super(principal, credentials);
        this.make = make;
    }

    public CustomAuthToken(AutoUser principal, String credentials, Collection<? extends GrantedAuthority> authorities, String make) {
        super(principal, credentials, authorities);
        this.make = make;
    }


    public String getMake() {
        return make;
    }
}
