package com.example.carental.validation;

import com.example.carental.dto.message.MessageRequestDTO;
import com.example.carental.exception.message.InvalidMessageException;
import com.example.carental.exception.message.MessageNotFoundException;
import com.example.carental.model.Message;
import com.example.carental.repository.MessageRepository;
import com.example.carental.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class MessageValidator {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageValidator(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    /**
     * @param messageRequestDTO
     */
    public void sendMessageValidation(MessageRequestDTO messageRequestDTO) {
        if (messageRequestDTO.getContent() == null || messageRequestDTO.getContent().length() > 500) {
            throw new InvalidMessageException("Message can not be null and can not have more than 500 characters length.");
        }
    }

    /**
     * @param messageId
     * @return Message with id = messageId or exception
     */
    public Message getByIdValidation(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new MessageNotFoundException("Can not found a message with id = " + messageId));
    }
}
