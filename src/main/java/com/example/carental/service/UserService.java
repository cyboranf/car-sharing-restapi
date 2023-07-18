package com.example.carental.service;

import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.dto.register.UserRegistrationRequest;
import com.example.carental.dto.user.UserRequestDTO;
import com.example.carental.dto.user.UserResponseDTO;
import com.example.carental.exception.role.RoleNotFoundException;
import com.example.carental.mapper.CarMapper;
import com.example.carental.mapper.UserMapper;
import com.example.carental.model.Role;
import com.example.carental.model.User;
import com.example.carental.repository.RoleRepository;
import com.example.carental.repository.UserRepository;
import com.example.carental.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final CarMapper carMapper;
    private final UserValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserMapper userMapper, CarMapper carMapper, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.carMapper = carMapper;
        this.userValidator = userValidator;
    }

    /**
     * @param registrationRequest
     * @return DTO of new User
     */
    public UserResponseDTO registerUser(UserRegistrationRequest registrationRequest) {
        userValidator.registerValidation(registrationRequest);

        User user = userMapper.fromRegistrationRequest(registrationRequest);
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RoleNotFoundException("Default user role not found."));
        user.setRoles(Collections.singleton(defaultRole));

        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    /**
     * @param userId
     * @return DTO of user with id = userId
     */
    public UserResponseDTO getUserById(Long userId) {
        return userMapper.toDTO(userValidator.validateUserById(userId));
    }

    /**
     * @param userId
     * @param userRequestDTO
     * @return DTO of updated User
     */
    public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
        userValidator.updateUserValidation(userId, userRequestDTO);

        User user = userMapper.fromDTO(userRequestDTO);

        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    /**
     * @param userId
     * @return deleted User
     */
    public UserResponseDTO deleteUser(Long userId) {
        User user = userValidator.validateUserById(userId);
        userRepository.delete(user);
        return userMapper.toDTO(user);
    }

    public List<CarResponseDTO> getUsersCars(Long userId) {
        User user = userValidator.validateUserById(userId);

        return user.getCars().stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @return dto of all users
     */
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }
}
