package com.example.carental.service;

import com.example.carental.model.AuthenticatedUser;
import com.example.carental.model.User;
import com.example.carental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByFirstName(name).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + name));

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + name);
        }

        // Fetch the roles associated with the user
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        // No need to encode the password here as it's already encoded in the database
        return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(), authorities);
    }
}