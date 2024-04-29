package com.example.demo.controller;

import com.example.demo.enitity.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    private final ObjectMapper objectMapper;
    @MessageMapping("/chat/message")
    public void message(String message) throws JsonProcessingException {
        var dto = objectMapper.readValue(message, ChatMessage.class);

        if (!dto.getType().equals("TALK")) {
            dto.setMessage(dto.getSender() + "님이 입장하셨습니다. 👋🏼");
        }

        messagingTemplate.convertAndSend("/sub/chat/room/"+dto.getRoomId(), message);
    }
}
