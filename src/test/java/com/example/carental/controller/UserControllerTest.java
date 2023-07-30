package com.example.carental.controller;

import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.dto.user.UserRequestDTO;
import com.example.carental.dto.user.UserResponseDTO;
import com.example.carental.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUser() {
        UserResponseDTO response = new UserResponseDTO();
        when(userService.getUserById(1L)).thenReturn(response);

        ResponseEntity<UserResponseDTO> result = userController.getUser(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testUpdateUser() {
        UserRequestDTO request = new UserRequestDTO();
        UserResponseDTO response = new UserResponseDTO();
        when(userService.updateUser(1L, request)).thenReturn(response);

        ResponseEntity<UserResponseDTO> result = userController.updateUser(1L, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testGetUsersCars() {
        CarResponseDTO carResponseDTO = new CarResponseDTO();
        List<CarResponseDTO> response = Collections.singletonList(carResponseDTO);
        when(userService.getUsersCars(1L)).thenReturn(response);

        ResponseEntity<List<CarResponseDTO>> result = userController.getUsersCars(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
