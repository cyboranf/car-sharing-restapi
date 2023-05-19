package com.example.carental.service;

import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.dto.user.UserRequestDTO;
import com.example.carental.dto.user.UserResponseDTO;
import com.example.carental.exception.ResourceNotFoundException;
import com.example.carental.model.Car;
import com.example.carental.model.CarFeature;
import com.example.carental.model.Role;
import com.example.carental.model.User;
import com.example.carental.repository.AddressRepository;
import com.example.carental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        User newUser = new User();
        newUser.setEmail(userRequestDTO.getEmail());
        newUser.setFirstName(userRequestDTO.getFirstName());
        newUser.setLastName(userRequestDTO.getLastName());
        newUser.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));  // encode password
        newUser.setContactsCount(userRequestDTO.getContactsCount());
        newUser.setMsgCount(userRequestDTO.getMsgCount());
        newUser.setActive(userRequestDTO.isActive());
        newUser.setAddress(addressRepository.findById(userRequestDTO.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + userRequestDTO.getAddressId())));

        User savedUser = userRepository.save(newUser);
        return mapUserToResponseDTO(savedUser);
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

    public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        user.setEmail(userRequestDTO.getEmail());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        user.setContactsCount(userRequestDTO.getContactsCount());
        user.setMsgCount(userRequestDTO.getMsgCount());
        user.setActive(userRequestDTO.isActive());
        user.setAddress(addressRepository.findById(userRequestDTO.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not fount with id: " + userRequestDTO.getAddressId())));

        User updatedUser = userRepository.save(user);
        return mapUserToResponseDTO(updatedUser);
    }

    public void deleteUser(Long userId){
        User user=userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id: "+userId));
        userRepository.delete(user);
    }

    public List<CarResponseDTO> getUsersCars(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return user.getCars().stream()
                .map(this::mapCarToCarResponseDTO)
                .collect(Collectors.toList());
    }

    private UserResponseDTO mapUserToResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setContactsCount(user.getContactsCount());
        dto.setMsgCount(user.getMsgCount());
        dto.setActive(user.isActive());
        dto.setAddressId(user.getAddress().getId());
        dto.setRoleIds(user.getRoles().stream().map(Role::getId).collect(Collectors.toSet()));  // map roles to roleIds

        return dto;
    }

    private CarResponseDTO mapCarToCarResponseDTO(Car car) {
        CarResponseDTO dto = new CarResponseDTO();
        dto.setId(car.getId());
        dto.setModel(car.getModel());
        dto.setDescription(car.getDescription());
        dto.setSeats(car.getSeats());

        // Assuming that Car class has getOwner() and the returned User object has getId() method
        if(car.getOwner() != null) {
            dto.setOwnerId(car.getOwner().getId());
        }

        if(car.getStatus() != null) {
            dto.setStatusId(car.getStatus().getId());
        }
        if(car.getType() != null) {
            dto.setTypeId(car.getType().getId());
        }
        if(car.getManufacturer() != null) {
            dto.setManufacturerId(car.getManufacturer().getId());
        }

        if(car.getFeatures() != null) {
            dto.setFeatureIds(car.getFeatures().stream()
                    .map(CarFeature::getId)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }
}
