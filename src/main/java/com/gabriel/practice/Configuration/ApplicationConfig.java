package com.gabriel.practice.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
    public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
        //Esto evita instanciar directamente DaoAuthenticationProvider
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByName(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* ESTE CODIGO QUEDO EN DESUSO CON SPRING SECURITY 6
     * SI LO ELIMINO EL PROGRAMA SE ROMPE
     */
    @Bean
    public AuthenticationProvider authenticationProvider (UserDetailsService userDetailsService)
    {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    } 
      
    /* 
     * Spring Security 6 (el que usa Spring Boot 3) marcó como deprecated varias cosas clásicas:
     * DaoAuthenticationProvider()
     * setUserDetailsService(...)
     * Esto se debe a que ahora la configuración de seguridad está más funcional (con lambdas y beans) y menos “imperativa”
     * Ya no se crea ni se configura a mano el DaoAuthenticationProvider.
     * Ahora Spring Security detecta que tenés un UserDetailsService y un PasswordEncoder como @Bean y arma solo el AuthenticationProvider
     * TODO LO YA MENCIONADO GENERA ERROR EN LA APP POR LO CUAL SE DESESTIMA Y SE UTILIZA AuthenticationProvider DE IGUAL MANERA
     */



    

}
