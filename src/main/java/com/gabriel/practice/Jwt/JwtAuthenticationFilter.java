package com.gabriel.practice.Jwt;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /*
     * OncePerRequestFilter es una clase abstracta que se utiliza para crear filtros
     * personalizados
     * vamos a extender de esta clase para garantizar que el filtro se ejecute solo
     * una vez por cada
     * solicitud HTTP incluso si hay multiples filtros dentro de la cadena de
     * filtros
     */

    @Override // Sobreescribimos la propiedad de la clase Abstracta y la personalizamos

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Este metodo va a realizar TODOS los filtros relacionados al Token (JWT)

        // Primero obtenemos el token y obtenemos el username
        final String token = getTokenFromRequest(request);
        final String username;

        // Si el token es nulo vamos a devolverle a la cadena de filtros el control
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        username = jwtService.getUsernameFromToken(token);

        // Si el token es nulo y no se encuentra en el SecurityContextHolder lo buscamos
        // en la DB
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Si el token es valido debo actualizar el SecurityContextHolder
            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        /*
         * Debo configurar los siguientes metodos en JwtService:
         * - getUsernameFromToken()
         * - isTokenValid
         */

        filterChain.doFilter(request, response);
    }

    /*
     * El siguiente metodo nos devuelve el token, y devuelve un String porque
     * el token es una cadena de caracteres
     * Requiere que le pasemos como parametro el request ya que en el encabezado
     * del request vamos a tener el token
     */

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        // Vamos a buscar en el encabezado la propiedad de autenticacion

        /*
         * Este encabezado String va a comenzar con la palabra Bearer
         * debemos verificar esto para retornar el token
         * ya que vamos a tener que extraer el token de esa cadena
         * de caracteres sin incorporar la palabra Bearer
         */
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            /*
             * Accedemos a una estructura de control para verificar lo mencionado
             * utilizamos la libreria StringUtils de springframeworks
             */
            return authHeader.substring(7); // El token comienza a partir de la posicion 7
        }
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

         return path.startsWith("/ws")
        || path.startsWith("/topic")
        || path.startsWith("/queue")
        || path.equals("/")
        || path.equals("/chat.html")
        || path.equals("/dashboard.html")
        || path.startsWith("/auth");
    }
}
