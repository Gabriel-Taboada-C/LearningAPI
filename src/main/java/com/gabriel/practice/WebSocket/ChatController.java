package com.gabriel.practice.WebSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String sendMessage(String message) {
        System.out.println("Llego el mensaje: " + message);
        return "Servidor dice: " + message;
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
