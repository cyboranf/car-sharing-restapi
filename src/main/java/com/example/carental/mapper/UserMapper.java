package com.example.carental.mapper;

import com.example.carental.model.User;
import com.example.carental.dto.user.UserRequestDTO;
import com.example.carental.dto.user.UserResponseDTO;
import com.example.carental.dto.register.UserRegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setContactsCount(user.getContactsCount());
        dto.setMsgCount(user.getMsgCount());
        dto.setActive(user.isActive());
        dto.setAddressId(user.getAddress().getId());
        return dto;
    }

    public User fromDTO(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setEmail(userRequestDTO.getEmail());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setPassword(userRequestDTO.getPassword());
        user.setContactsCount(userRequestDTO.getContactsCount());
        user.setMsgCount(userRequestDTO.getMsgCount());
        user.setActive(userRequestDTO.isActive());
        return user;
    }
    public User fromResDTO(UserResponseDTO userRequestDTO) {
        User user = new User();
        user.setEmail(userRequestDTO.getEmail());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setContactsCount(userRequestDTO.getContactsCount());
        user.setMsgCount(userRequestDTO.getMsgCount());
        user.setActive(userRequestDTO.isActive());
        return user;
    }


    public User fromRegistrationRequest(UserRegistrationRequest userRegistrationRequest) {
        User user = new User();
        user.setEmail(userRegistrationRequest.getEmail());
        user.setFirstName(userRegistrationRequest.getFirstName());
        user.setLastName(userRegistrationRequest.getLastName());
        user.setPassword(userRegistrationRequest.getPassword());
        return user;
    }
}
