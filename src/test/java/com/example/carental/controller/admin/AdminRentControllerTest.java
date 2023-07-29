package com.example.carental.controller.admin;

import com.example.carental.controller.admin.AdminRentController;
import com.example.carental.dto.rent.RentResponseDTO;
import com.example.carental.service.RentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdminRentControllerTest {

    @InjectMocks
    private AdminRentController adminRentController;

    @Mock
    private RentService rentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRentsByUserId() {
        RentResponseDTO response1 = new RentResponseDTO();
        RentResponseDTO response2 = new RentResponseDTO();
        List<RentResponseDTO> responses = Arrays.asList(response1, response2);
        when(rentService.getRentsByUserId(1L)).thenReturn(responses);

        ResponseEntity<List<RentResponseDTO>> result = adminRentController.getRentsByUserId(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }

    @Test
    public void testGetRent() {
        RentResponseDTO response = new RentResponseDTO();
        when(rentService.getRentById(1L)).thenReturn(response);

        ResponseEntity<RentResponseDTO> result = adminRentController.getRent(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

}
