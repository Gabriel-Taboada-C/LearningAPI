package com.gabriel.practice.User;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByName(name)
            .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado" + name));
        
        //Como tengo un Role de tipo Enum
        String roleName = "ROLE_" + user.getRol().name();

        return new org.springframework.security.core.userdetails.User(
            user.getName(),
            user.getPassword(), 
            Collections.singletonList(new SimpleGrantedAuthority(roleName))
        );
    
    }

}
