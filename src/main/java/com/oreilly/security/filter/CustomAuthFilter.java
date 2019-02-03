package com.oreilly.security.filter;

import com.oreilly.security.token.CustomAuthToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomAuthFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = super.obtainUsername(request);
        String password = super.obtainPassword(request);

        String make = request.getParameter("make");

        CustomAuthToken token = new CustomAuthToken(username, password, make);
        super.setDetails(request, token);

        return getAuthenticationManager().authenticate(token);
    }
}
