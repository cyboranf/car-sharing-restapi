package com.example.carental.controller;

import com.example.carental.dto.rent.RentRequestDTO;
import com.example.carental.dto.rent.RentResponseDTO;
import com.example.carental.service.RentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RentControllerTest {

    @InjectMocks
    private RentController rentController;

    @Mock
    private RentService rentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRent() {
        RentRequestDTO request = new RentRequestDTO();
        RentResponseDTO response = new RentResponseDTO();
        when(rentService.rentCar(request)).thenReturn(response);

        ResponseEntity<RentResponseDTO> result = rentController.createRent(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testUpdateRent() {
        RentRequestDTO request = new RentRequestDTO();
        RentResponseDTO response = new RentResponseDTO();
        when(rentService.updateRent(1L, request)).thenReturn(response);

        ResponseEntity<RentResponseDTO> result = rentController.updateRent(1L, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

}
