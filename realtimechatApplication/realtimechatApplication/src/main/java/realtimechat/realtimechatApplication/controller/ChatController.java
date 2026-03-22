package realtimechat.realtimechatApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

import realtimechat.realtimechatApplication.dataBase.ChatMessageEntity;
import realtimechat.realtimechatApplication.dataBase.ChatMessageRepository;

@Controller
public class ChatController {

    // Inject the database repository
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @MessageMapping("/chat.sendMessage") // Listens for incoming messages from clients
    @SendTo("/topic/public") // Broadcasts the return value to all subscribers
    public ChatMessageEntity sendMessage(@Payload ChatMessageEntity chatMessage) {
        
        // 1. Save the incoming message to the MySQL database
        ChatMessageEntity savedMessage = chatMessageRepository.save(chatMessage);
        
        // 2. Broadcast the saved message (now with a database ID and timestamp) to the room
        return savedMessage; 
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessageEntity addUser(@Payload ChatMessageEntity chatMessage) {
        // Optional: Save "User Joined" events to the database as well
        return chatMessageRepository.save(chatMessage);
    }
}