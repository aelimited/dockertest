package com.example.demo.enitity;

import com.example.demo.sevice.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.UUID;

//dto
@Getter
public class ChatRoom {

    private String roomId;
    private String name;
    private HashSet<WebSocketSession> sessions = new HashSet<>();

    public ChatRoom() {

    }

    public static ChatRoom create(String name) {
       ChatRoom chatRoom = new ChatRoom();
       chatRoom.roomId = UUID.randomUUID().toString();
       chatRoom.name = name;
       return chatRoom;
   }

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
        if (chatMessage.getMessageType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다 👋🏼");
        }
        sendMessage(chatMessage, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }

}
