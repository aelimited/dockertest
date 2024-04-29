package com.example.demo.enitity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public enum MessageType{
        ENTER, TALK
    }

    private MessageType messageType;
    private String id;
    private String sender;
    private String message;

    }
