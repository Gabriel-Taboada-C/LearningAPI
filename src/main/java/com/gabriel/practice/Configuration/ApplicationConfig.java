package com.gabriel.practice.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gabriel.practice.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    public AuthenticationManager authebAuthenticationManager (AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
        //Esto evita instanciar directamente DaoAuthenticationProvider
    }


    /* 
     * Spring Security 6 (el que usa Spring Boot 3) marcó como deprecated varias cosas clásicas:
     * DaoAuthenticationProvider()
     * setUserDetailsService(...)
     * Esto se debe a que ahora la configuración de seguridad está más funcional (con lambdas y beans) y menos “imperativa”
     * Ya no se crea ni se configura a mano el DaoAuthenticationProvider.
     * Ahora Spring Security detecta que tenés un UserDetailsService y un PasswordEncoder como @Bean y arma solo el AuthenticationProvider.
     */

  /*   
    ESTE CODIGO QUEDO EN DESUSO CON SPRING SECURITY 6
    @Bean
    public AuthenticationProvider authenticationProvider ()
    {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    } */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByName(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
