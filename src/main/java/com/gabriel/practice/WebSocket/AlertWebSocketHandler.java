package com.gabriel.practice.WebSocket;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriComponentsBuilder;

import com.gabriel.practice.Jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AlertWebSocketHandler extends TextWebSocketHandler {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
  
        String token = UriComponentsBuilder.fromUri(session.getUri())
        .build()
        .getQueryParams()
        .getFirst("token");

         if (token == null) {
            session.close();
            return;
        }
        
        String username = jwtService.getUsernameFromToken(token);

        UserDetails userDetails =
                        userDetailsService.loadUserByUsername(username);

        if (!jwtService.isTokenValid(token, userDetails)) {
            session.close();
            return;
        }

        session.sendMessage(new TextMessage("Bienvenido " + username));
    }
}

