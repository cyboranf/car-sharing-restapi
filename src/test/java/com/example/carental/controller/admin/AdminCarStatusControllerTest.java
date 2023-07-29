package com.example.carental.controller.admin;

import com.example.carental.controller.admin.AdminCarStatusController;
import com.example.carental.dto.carStatus.CarStatusRequestDTO;
import com.example.carental.dto.carStatus.CarStatusResponseDTO;
import com.example.carental.service.CarStatusService;
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

public class AdminCarStatusControllerTest {

    @InjectMocks
    private AdminCarStatusController adminCarStatusController;

    @Mock
    private CarStatusService carStatusService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCarStatus() {
        CarStatusRequestDTO request = new CarStatusRequestDTO();
        CarStatusResponseDTO response = new CarStatusResponseDTO();
        when(carStatusService.createCarStatus(request)).thenReturn(response);

        ResponseEntity<CarStatusResponseDTO> result = adminCarStatusController.createCarStatus(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testGetAllCarStatuses() {
        CarStatusResponseDTO response1 = new CarStatusResponseDTO();
        CarStatusResponseDTO response2 = new CarStatusResponseDTO();
        List<CarStatusResponseDTO> responses = Arrays.asList(response1, response2);
        when(carStatusService.getAllCarStatuses()).thenReturn(responses);

        ResponseEntity<List<CarStatusResponseDTO>> result = adminCarStatusController.getAllCarStatuses();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }

    @Test
    public void testUpdateCarStatus() {
        CarStatusRequestDTO request = new CarStatusRequestDTO();
        CarStatusResponseDTO response = new CarStatusResponseDTO();
        when(carStatusService.updateCarStatus(1L, request)).thenReturn(response);

        ResponseEntity<CarStatusResponseDTO> result = adminCarStatusController.updateCarStatus(1L, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testDeleteCarStatus() {
        CarStatusResponseDTO response = new CarStatusResponseDTO();
        when(carStatusService.deleteCarStatus(1L)).thenReturn(response);

        ResponseEntity<CarStatusResponseDTO> result = adminCarStatusController.deleteCarStatus(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
