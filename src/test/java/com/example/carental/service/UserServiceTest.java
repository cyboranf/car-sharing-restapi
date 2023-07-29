package com.example.carental.service;

import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.dto.register.UserRegistrationRequest;
import com.example.carental.dto.user.UserRequestDTO;
import com.example.carental.dto.user.UserResponseDTO;
import com.example.carental.mapper.CarMapper;
import com.example.carental.mapper.UserMapper;
import com.example.carental.model.Car;
import com.example.carental.model.Role;
import com.example.carental.model.User;
import com.example.carental.repository.RoleRepository;
import com.example.carental.repository.UserRepository;
import com.example.carental.validation.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @Mock
    private CarMapper carMapper;

    @Mock
    private UserValidator userValidator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setFirstName("testUser");
        request.setPassword("testPassword");

        User user = new User();
        user.setFirstName("testUser");
        user.setPassword("testPassword");

        Role role = new Role();
        role.setName("ROLE_USER");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setFirstName("testUser");

        when(userMapper.fromRegistrationRequest(any(UserRegistrationRequest.class))).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(role));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDTO(any(User.class))).thenReturn(userResponseDTO);

        UserResponseDTO response = userService.registerUser(request);

        assertEquals("testUser", response.getFirstName());
    }


    @Test
    public void testGetUserById() {
        User user = new User();
        user.setFirstName("testUser");
        user.setPassword("testPassword");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setFirstName("testUser");

        when(userValidator.validateUserById(anyLong())).thenReturn(user);
        when(userMapper.toDTO(any(User.class))).thenReturn(userResponseDTO);

        UserResponseDTO response = userService.getUserById(1L);

        assertEquals("testUser", response.getFirstName());
    }


    @Test
    public void testUpdateUser() {
        UserRequestDTO request = new UserRequestDTO();
        request.setFirstName("testUser");
        request.setPassword("testPassword");

        User user = new User();
        user.setFirstName("testUser");
        user.setPassword("testPassword");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setFirstName("testUser");

        when(userMapper.fromDTO(any(UserRequestDTO.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDTO(any(User.class))).thenReturn(userResponseDTO);

        UserResponseDTO response = userService.updateUser(1L, request);

        assertEquals("testUser", response.getFirstName());
    }


    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setFirstName("testUser");
        user.setPassword("testPassword");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setFirstName("testUser");

        when(userValidator.validateUserById(anyLong())).thenReturn(user);
        when(userMapper.toDTO(any(User.class))).thenReturn(userResponseDTO);

        UserResponseDTO response = userService.deleteUser(1L);

        assertEquals("testUser", response.getFirstName());
    }


    @Test
    public void testGetUsersCars() {
        User user = new User();
        user.setFirstName("testUser");
        user.setPassword("testPassword");

        Car car = new Car();
        car.setModel("testCar");
        user.setCars(Collections.singletonList(car));

        CarResponseDTO carResponseDTO = new CarResponseDTO();
        carResponseDTO.setModel("testCar");

        when(userValidator.validateUserById(anyLong())).thenReturn(user);
        when(carMapper.toDTO(any(Car.class))).thenReturn(carResponseDTO);

        List<CarResponseDTO> response = userService.getUsersCars(1L);

        assertEquals(1, response.size());
        assertEquals("testCar", response.get(0).getModel());
    }


    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setFirstName("testUser1");
        user1.setPassword("testPassword1");

        User user2 = new User();
        user2.setFirstName("testUser2");
        user2.setPassword("testPassword2");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(userMapper.toDTO(any(User.class))).thenReturn(new UserResponseDTO());

        List<UserResponseDTO> response = userService.getAllUsers();

        assertEquals(2, response.size());
    }
}
