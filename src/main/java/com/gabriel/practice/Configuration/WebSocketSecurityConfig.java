/* package com.gabriel.practice.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer{

// El cliente podría suscribirse a otro chat si no hay seguridad. Por eso se debe configurar la seguridad por rol 


    // 🔹 Estrategia 1: Canales por rol (simple)

    @Override
    protected void configureInbound (MessageSecurityMetadataSourceRegistry messages) {
        messages
                .simpSubscribeDestMatchers("/topic/admin/**").hasRole("ADMIN")
                .simpSubscribeDestMatchers("/topic/user/**").hasAnyRole("USER", "ADMIN")
                .anyMessage().authenticated();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }

    // Estrategia 2: Usuario privado + rol (recomendada)

    for (User user:usuariosAdmin) {
        messagingTemplate.convertAndSendToUser(
            user.getUsername(),
            "/queue/notifications",
            "Alerta critica"); 
        }
} */
