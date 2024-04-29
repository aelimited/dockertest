package com.example.demo.controller;

import com.example.demo.enitity.ChatMessage;
import com.example.demo.enitity.ChatRoom;
import com.example.demo.sevice.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (message.getMessageType().equals(ChatMessage.MessageType.ENTER)) {
            message.setMessage(message.getSender() + "님이 입장하셨습니다. 👋🏼");
        }
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getId(), message);
    }
}
