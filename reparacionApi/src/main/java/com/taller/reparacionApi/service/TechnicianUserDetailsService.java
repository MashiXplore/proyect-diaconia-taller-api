package com.taller.reparacionApi.service;

import com.taller.reparacionApi.model.Technician;
import com.taller.reparacionApi.repository.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnicianUserDetailsService implements UserDetailsService {

    private final TechnicianRepository technicianRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Technician technician = technicianRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + technician.getRole().toUpperCase())
        );

        return User.builder()
                .username(technician.getUsername())
                .password(technician.getPassword())
                .authorities(authorities)
                .disabled(!"ACTIVE".equals(technician.getStatus()))
                .build();
    }
}