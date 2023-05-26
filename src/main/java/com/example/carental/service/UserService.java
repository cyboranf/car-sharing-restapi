package com.example.carental.service;

import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.dto.register.UserRegistrationRequest;
import com.example.carental.dto.user.UserRequestDTO;
import com.example.carental.dto.user.UserResponseDTO;
import com.example.carental.exception.ResourceNotFoundException;
import com.example.carental.exception.UserUpdateException;
import com.example.carental.mapper.UserMapper;
import com.example.carental.model.Car;
import com.example.carental.model.Role;
import com.example.carental.model.User;
import com.example.carental.repository.AddressRepository;
import com.example.carental.repository.RoleRepository;
import com.example.carental.repository.UserRepository;
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
    private final AddressRepository addressRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User registerUser(UserRegistrationRequest registrationRequest) {
        // Check if the username already exists
        if (userRepository.existsByFirstName(registrationRequest.getFirstName()) && userRepository.existsByLastName(registrationRequest.getLastName())) {
            throw new IllegalArgumentException("You have an account");
        }

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        // Create a new User object with the registration details
        User newUser = new User();
        newUser.setFirstName(registrationRequest.getFirstName());
        newUser.setLastName(registrationRequest.getLastName());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        // Assign a default role to the user
        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new IllegalArgumentException("Default user role not found."));
        newUser.setRoles(Collections.singleton(defaultRole));

        return userRepository.save(newUser);
    }
    public boolean existsByFirstName(String firstName) {
        return userRepository.existsByFirstName(firstName);
    }
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapUserToResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return mapUserToResponseDTO(user);
    }

    public User getUser2(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
            user.setEmail(userRequestDTO.getEmail());
            user.setFirstName(userRequestDTO.getFirstName());
            user.setLastName(userRequestDTO.getLastName());
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            User updatedUser = userRepository.save(user);
            return mapUserToResponseDTO(updatedUser);
        } catch (Exception e) {
            throw new UserUpdateException("Failed to update the user.", e);
        }
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        userRepository.delete(user);
    }

    public List<CarResponseDTO> getUsersCars(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return user.getCars().stream()
                .map(this::mapCarToCarResponseDTO)
                .collect(Collectors.toList());
    }
    public UserResponseDTO mapUserToResponseDTO(User user){
        return userMapper.mapUserToResponseDTO(user);
    }
    public CarResponseDTO mapCarToCarResponseDTO(Car car){
        return userMapper.mapCarToCarResponseDTO(car);
    }
}
