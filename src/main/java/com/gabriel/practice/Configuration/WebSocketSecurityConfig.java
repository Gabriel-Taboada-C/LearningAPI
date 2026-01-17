package com.gabriel.practice.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.gabriel.practice.Jwt.JwtStompInterceptor;

// El cliente podría suscribirse a otro chat si no hay seguridad. Por eso se debe configurar la seguridad por rol 
@Configuration
@EnableWebSocket
public class WebSocketSecurityConfig implements WebSocketMessageBrokerConfigurer {

    // Registramos el interceptor JWT en el canal de entrada de mensajes
    @Autowired
    private JwtStompInterceptor jwtStompInterceptor;

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(jwtStompInterceptor);
        
}
}
