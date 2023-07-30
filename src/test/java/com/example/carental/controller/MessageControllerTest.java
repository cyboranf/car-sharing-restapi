package com.example.carental.controller;

import com.example.carental.dto.message.MessageRequestDTO;
import com.example.carental.dto.message.MessageResponseDTO;
import com.example.carental.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MessageControllerTest {

    @InjectMocks
    private MessageController messageController;

    @Mock
    private MessageService messageService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendMessage() {
        MessageRequestDTO request = new MessageRequestDTO();
        MessageResponseDTO response = new MessageResponseDTO();
        when(messageService.sendMessage(request)).thenReturn(response);

        ResponseEntity<MessageResponseDTO> result = messageController.sendMessage(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testGetMessagesBetweenUsers() {
        MessageResponseDTO response1 = new MessageResponseDTO();
        MessageResponseDTO response2 = new MessageResponseDTO();
        List<MessageResponseDTO> responses = Arrays.asList(response1, response2);
        when(messageService.getMessagesBetweenUsers(1L, 2L)).thenReturn(responses);

        ResponseEntity<List<MessageResponseDTO>> result = messageController.getMessagesBetweenUsers(1L, 2L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }

    @Test
    public void testDeleteMessage() {
        MessageResponseDTO response = new MessageResponseDTO();
        when(messageService.deleteMessage(1L)).thenReturn(response);

        ResponseEntity<MessageResponseDTO> result = messageController.deleteMessage(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
