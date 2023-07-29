package com.example.carental.controller;

import com.example.carental.dto.message.MessageRequestDTO;
import com.example.carental.dto.message.MessageResponseDTO;
import com.example.carental.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * @param messageRequestDTO
     * @return DTO of new Message
     */
    @PostMapping("/messages")
    public ResponseEntity<MessageResponseDTO> sendMessage(@RequestBody MessageRequestDTO messageRequestDTO) {
        return ResponseEntity.ok(messageService.sendMessage(messageRequestDTO));
    }

    /**
     * @param senderId
     * @param recipientId
     * @return DTO of chat between 2 Users (Users see their chat)
     */
    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesBetweenUsers(@PathVariable Long senderId, @PathVariable Long recipientId) {
        return ResponseEntity.ok(messageService.getMessagesBetweenUsers(senderId, recipientId));
    }

    /**
     * @param messageId
     * @return DTO of deleted Message
     */

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<MessageResponseDTO> deleteMessage(@PathVariable Long messageId) {
        return ResponseEntity.ok(messageService.deleteMessage(messageId));
    }
}
