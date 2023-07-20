package com.example.carental.service;

import com.example.carental.model.Message;
import com.example.carental.model.User;
import com.example.carental.dto.message.MessageRequestDTO;
import com.example.carental.dto.message.MessageResponseDTO;
import com.example.carental.mapper.MessageMapper;
import com.example.carental.repository.MessageRepository;
import com.example.carental.repository.UserRepository;
import com.example.carental.validation.MessageValidator;
import com.example.carental.validation.UserValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;
    private final MessageValidator messageValidator;
    private final UserValidator userValidator;


    public MessageService(MessageRepository messageRepository, UserRepository userRepository, MessageMapper messageMapper, MessageValidator messageValidator, UserValidator userValidator) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageMapper = messageMapper;
        this.messageValidator = messageValidator;
        this.userValidator = userValidator;
    }

    /**
     * @param messageRequestDTO
     * @return DTO of new message
     */
    public MessageResponseDTO sendMessage(MessageRequestDTO messageRequestDTO) {
        User sender = userValidator.validateUserById(messageRequestDTO.getSenderId());
        User recipient = userValidator.validateUserById(messageRequestDTO.getRecipientId());
        messageValidator.sendMessageValidation(messageRequestDTO);

        Message message = messageMapper.fromDTO(messageRequestDTO);

        message.setSender(sender);
        message.setRecipient(recipient);
        message.setSentAt(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);

        return messageMapper.toDTO(savedMessage);
    }

    /**
     * @param senderId
     * @param recipientId
     * @return DTO of all messages between users
     */
    public List<MessageResponseDTO> getMessagesBetweenUsers(Long senderId, Long recipientId) {
        messageValidator.getByIdValidation(senderId);
        messageValidator.getByIdValidation(recipientId);
        return messageRepository.findMessagesBetweenUsers(senderId, recipientId).stream()
                .map(messageMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @return get all messages (method for ADMIN)
     */
    public List<MessageResponseDTO> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(messageMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param messageId
     * @return DTO of message by id = messageId
     */
    public MessageResponseDTO getMessage(Long messageId) {
        Message message = messageValidator.getByIdValidation(messageId);
        return messageMapper.toDTO(message);
    }

    /**
     * @param id
     * @return DTO of deleted message
     */
    public MessageResponseDTO deleteMessage(Long id) {
        Message deletedMessage = messageValidator.getByIdValidation(id);
        messageRepository.delete(deletedMessage);
        return messageMapper.toDTO(deletedMessage);
    }
}
