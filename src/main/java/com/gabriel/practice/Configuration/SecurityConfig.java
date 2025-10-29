package com.gabriel.practice.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                /* .headers(headers -> headers.frameOptions().disable()) */ // habilitar frames (necesario para H2)
                // Spring Security 6.1+ frameOptions() quedó deprecated.
                // Lo reemplazaron por la API frameOptions(Customizer).
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .csrf(csrf -> csrf
                        .disable()) // Anular la proteccion csrf
                /*
                 * CSRF: Cros-Site Request Forgery es una medida de seguridad que se utiliza
                 * para agregar a las solicitudes POST
                 * una autenticacion basada en un token csrf value, pero vamos a utilizar JWT
                 */
                .authorizeHttpRequests(authRequest -> authRequest
                        // Login publico
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        // Solo algunos roles pueden agregar nuevos usuarios
                        .requestMatchers(HttpMethod.POST, "/auth/register").hasAnyRole("ADMIN", "USER_RRHH", "USER_CALI", "USER_PROD")
                        // h2 publico
                        .requestMatchers("/h2-console/**").permitAll()
                        // Solo ADMIN y RRHH pueden leer datos de usuarios
                        .requestMatchers(HttpMethod.GET, "users/**").hasAnyRole("ADMIN","USER_RRHH")
                        // Solo el Rol ADMIN puede modificar datos de usuarios
                        .requestMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                /* .formLogin(withDefaults()) Formulario de Login que provee Spring Security, no usaremos el por defecto */
                .sessionManagement(sessionManager ->
                        sessionManager
                          .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)      
                .build();
    }
}
