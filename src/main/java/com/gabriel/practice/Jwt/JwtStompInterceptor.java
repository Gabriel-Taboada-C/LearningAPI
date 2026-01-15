package com.gabriel.practice.Jwt;

import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

// Creamos el interceptor STOMP para que reciba y valide el JWT en cada mensaje, ya que WebSocket no es de tipo REST, es decir, no son mensajes HTTP estaticos
@Component
@RequiredArgsConstructor
public class JwtStompInterceptor implements ChannelInterceptor {

    private final JwtService jwtService; // Servicio JWT creado anteriormente para recibir y validar el token
    private final UserDetailsService userDetailsService; // Servicio de Spring Security para cargar los detalles del usuario

    @Override
    public Message<?> preSend( // Se usa @NonNull para evitar valores nulos
                               // No usamos @Nullable porque rompe el contrato, es decir, no se puede devolver null
                              @NonNull Message<?> message,
                              @NonNull MessageChannel channel) 
    {
        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (accessor == null) {
            return message;
        }

        // SOLO interceptamos el CONNECT
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {

            String authHeader = accessor.getFirstNativeHeader("Authorization");

            // 👉 SI NO HAY TOKEN: dejamos conectar SIN usuario
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.println("⚠️ STOMP CONNECT sin JWT");
                return message;
            }

            try {
                String token = authHeader.substring(7);
                String username = jwtService.getUsernameFromToken(token);

                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(username);

                if (jwtService.isTokenValid(token, userDetails)) {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    accessor.setUser(authentication);
                    System.out.println("✅ STOMP autenticado: " + username);
                }

            } catch (Exception e) {
                // 🔴 NUNCA lanzar excepción
                System.out.println("❌ Error JWT STOMP: " + e.getMessage());
            }
        }

        return message;
    }
}
