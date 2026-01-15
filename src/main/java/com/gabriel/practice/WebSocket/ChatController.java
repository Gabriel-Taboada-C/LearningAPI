package com.gabriel.practice.WebSocket;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String sendMessage(String message, Principal principal) {
        System.out.println("Llego el mensaje: " + message);
        String user = (principal != null)
            ? principal.getName()
            : "Anonim";

    return user + ": " + message;
    }

/*     @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendPrivateMessage(String username, String message){
        messagingTemplate.convertAndSendToUser(
            username,
            "/queue/notifications",
            message); 
    } */
}
