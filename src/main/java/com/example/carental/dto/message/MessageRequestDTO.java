package com.example.carental.dto.message;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageRequestDTO {
    private Long senderId;
    private Long recipientId;
    private String content;
    private LocalDateTime sentAt;
}
