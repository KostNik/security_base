package com.oreilly.security.service;

import com.oreilly.security.domain.entities.AutoUser;
import com.oreilly.security.domain.repositories.AutoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("customUserDetailService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AutoUserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AutoUser autoUser = repository.findByUsername(username);
        return new User(autoUser.getUsername(), autoUser.getPassword(), AuthorityUtils.createAuthorityList(autoUser.getRole()));
    }

}
