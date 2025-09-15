package com.gabriel.practice.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gabriel.practice.Jwt.JwtAuthenticationFilter;

/* Agregar importacion a mano */
/* Elimino dependencia */
/* import static org.springframework.security.config.Customizer.withDefaults; */

import lombok.RequiredArgsConstructor;

/*
* @Configuration indica que esta clase va a tener
* métodos que van a estar anotados con @Bean
* los cuales se van a utilizar para crear y configurar
* los objetos requeridos por nuestra aplicación
*/
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final AuthenticationProvider authProvider;

    /*
     * SecurityFilterChain es un método que va a contener toda
     * la cadena de filtros que se van a ir ejecutando
     */

    // Configuración de endpoints públicos y protegidos:
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http // Retorno el http siempre y cuando pase una cadena de filtros siguientes:
                .headers(headers -> headers.frameOptions().disable()) // habilitar frames (necesario para H2)
                .csrf(csrf -> csrf
                        .disable()) // Anular la proteccion csrf
                /*
                 * CSRF: Cros-Site Request Forgery es una medida de seguridad que se utiliza
                 * para agregar a las solicitudes POST
                 * una autenticacion basada en un token csrf value, pero vamos a utilizar JWT
                 */
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers("/auth/**").permitAll() // Login y registro publicos
                        .requestMatchers("/h2-console/**").permitAll() // h2 publico
                        .anyRequest().authenticated())
                /* .formLogin(withDefaults()) Formulario de Login que provee Spring Security */
                .sessionManagement(sessionManager ->
                        sessionManager
                          .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)      
                .build();
    }
}
