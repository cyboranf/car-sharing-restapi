package com.example.carental.mapper;

import com.example.carental.model.Message;
import com.example.carental.dto.message.MessageRequestDTO;
import com.example.carental.dto.message.MessageResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public MessageResponseDTO toDTO(Message message) {
        MessageResponseDTO dto = new MessageResponseDTO();
        dto.setId(message.getId());
        dto.setSenderId(message.getSender().getId());
        dto.setRecipientId(message.getRecipient().getId());
        dto.setContent(message.getContent());
        dto.setSentAt(message.getSentAt());
        return dto;
    }

    public Message fromDTO(MessageRequestDTO messageRequestDTO) {
        Message message = new Message();
        message.setContent(messageRequestDTO.getContent());
        message.setSentAt(messageRequestDTO.getSentAt());
        return message;
    }
}
