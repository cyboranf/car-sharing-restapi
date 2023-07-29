package com.example.carental.service;

import com.example.carental.dto.message.MessageRequestDTO;
import com.example.carental.dto.message.MessageResponseDTO;
import com.example.carental.mapper.MessageMapper;
import com.example.carental.model.Message;
import com.example.carental.model.User;
import com.example.carental.repository.MessageRepository;
import com.example.carental.repository.UserRepository;
import com.example.carental.validation.MessageValidator;
import com.example.carental.validation.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MessageServiceTest {

    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageMapper messageMapper;

    @Mock
    private MessageValidator messageValidator;

    @Mock
    private UserValidator userValidator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendMessage() {
        MessageRequestDTO request = new MessageRequestDTO();
        request.setSenderId(1L);
        request.setRecipientId(2L);
        request.setContent("Hello!");

        User sender = new User();
        sender.setId(request.getSenderId());

        User recipient = new User();
        recipient.setId(request.getRecipientId());

        Message message = new Message();
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setContent(request.getContent());
        message.setSentAt(LocalDateTime.now());

        MessageResponseDTO responseDTO = new MessageResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setSenderId(request.getSenderId());
        responseDTO.setRecipientId(request.getRecipientId());
        responseDTO.setContent(request.getContent());
        responseDTO.setSentAt(message.getSentAt());

        when(userValidator.validateUserById(anyLong())).thenReturn(sender, recipient);
        when(messageMapper.fromDTO(any(MessageRequestDTO.class))).thenReturn(message);
        when(messageRepository.save(any(Message.class))).thenReturn(message);
        when(messageMapper.toDTO(any(Message.class))).thenReturn(responseDTO);

        MessageResponseDTO response = messageService.sendMessage(request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testGetMessagesBetweenUsers() {
        Long senderId = 1L;
        Long recipientId = 2L;

        Message message = new Message();
        message.setSender(new User());
        message.getSender().setId(senderId);
        message.setRecipient(new User());
        message.getRecipient().setId(recipientId);

        MessageResponseDTO responseDTO = new MessageResponseDTO();
        responseDTO.setSenderId(senderId);
        responseDTO.setRecipientId(recipientId);

        when(messageRepository.findMessagesBetweenUsers(anyLong(), anyLong())).thenReturn(Arrays.asList(message));
        when(messageMapper.toDTO(any(Message.class))).thenReturn(responseDTO);

        List<MessageResponseDTO> response = messageService.getMessagesBetweenUsers(senderId, recipientId);

        assertEquals(1, response.size());
        assertEquals(responseDTO, response.get(0));
    }

    @Test
    public void testGetAllMessages() {
        Message message = new Message();
        message.setSender(new User());
        message.setRecipient(new User());
        message.setContent("Hello!");

        MessageResponseDTO responseDTO = new MessageResponseDTO();
        responseDTO.setSenderId(1L);
        responseDTO.setRecipientId(2L);
        responseDTO.setContent("Hello!");

        when(messageRepository.findAll()).thenReturn(Arrays.asList(message));
        when(messageMapper.toDTO(any(Message.class))).thenReturn(responseDTO);

        List<MessageResponseDTO> response = messageService.getAllMessages();

        assertEquals(1, response.size());
        assertEquals(responseDTO, response.get(0));
    }

    @Test
    public void testGetMessage() {
        Long messageId = 1L;

        Message message = new Message();
        message.setId(messageId);
        message.setSender(new User());
        message.setRecipient(new User());
        message.setContent("Hello!");

        MessageResponseDTO responseDTO = new MessageResponseDTO();
        responseDTO.setId(messageId);
        responseDTO.setSenderId(1L);
        responseDTO.setRecipientId(2L);
        responseDTO.setContent("Hello!");

        when(messageValidator.getByIdValidation(anyLong())).thenReturn(message);
        when(messageMapper.toDTO(any(Message.class))).thenReturn(responseDTO);

        MessageResponseDTO response = messageService.getMessage(messageId);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testDeleteMessage() {
        Long messageId = 1L;

        Message message = new Message();
        message.setId(messageId);
        message.setSender(new User());
        message.setRecipient(new User());
        message.setContent("Hello!");

        MessageResponseDTO responseDTO = new MessageResponseDTO();
        responseDTO.setId(messageId);
        responseDTO.setSenderId(1L);
        responseDTO.setRecipientId(2L);
        responseDTO.setContent("Hello!");

        when(messageValidator.getByIdValidation(anyLong())).thenReturn(message);
        when(messageMapper.toDTO(any(Message.class))).thenReturn(responseDTO);

        MessageResponseDTO response = messageService.deleteMessage(messageId);

        assertEquals(responseDTO, response);

        verify(messageRepository, times(1)).delete(message);
    }

}