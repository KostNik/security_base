package com.oreilly.security.service;

import com.oreilly.security.domain.entities.AutoUser;
import com.oreilly.security.domain.repositories.AutoUserRepository;
import com.oreilly.security.token.CustomAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;

@Controller("customAuthProvider")
public class CustomAuthProvider implements AuthenticationProvider {

    @Autowired
    private AutoUserRepository repository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthToken token = (CustomAuthToken) authentication;
        AutoUser autoUser = repository.findByUsername(token.getName());
        if (autoUser == null
                || !autoUser.getPassword().equalsIgnoreCase(token.getCredentials().toString())
                || !token.getMake().equalsIgnoreCase("subaru")) {
            throw new BadCredentialsException("The cred are invalid");
        }
        return new CustomAuthToken(autoUser, autoUser.getPassword(), autoUser.getAuthorities(), token.getMake());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthToken.class.equals(authentication);
    }
}
