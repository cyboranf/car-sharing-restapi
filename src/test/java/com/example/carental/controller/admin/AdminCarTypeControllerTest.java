package com.example.carental.controller.admin;

import com.example.carental.controller.admin.AdminCarTypeController;
import com.example.carental.dto.carType.CarTypeRequestDTO;
import com.example.carental.dto.carType.CarTypeResponseDTO;
import com.example.carental.service.CarTypeService;
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

public class AdminCarTypeControllerTest {

    @InjectMocks
    private AdminCarTypeController adminCarTypeController;

    @Mock
    private CarTypeService carTypeService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCarType() {
        CarTypeRequestDTO request = new CarTypeRequestDTO();
        CarTypeResponseDTO response = new CarTypeResponseDTO();
        when(carTypeService.createCarType(request)).thenReturn(response);

        ResponseEntity<CarTypeResponseDTO> result = adminCarTypeController.createCarType(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testGetAllCarTypes() {
        CarTypeResponseDTO response1 = new CarTypeResponseDTO();
        CarTypeResponseDTO response2 = new CarTypeResponseDTO();
        List<CarTypeResponseDTO> responses = Arrays.asList(response1, response2);
        when(carTypeService.getAllCarTypes()).thenReturn(responses);

        ResponseEntity<List<CarTypeResponseDTO>> result = adminCarTypeController.getAllCarTypes();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }

    @Test
    public void testUpdateCarType() {
        CarTypeRequestDTO request = new CarTypeRequestDTO();
        CarTypeResponseDTO response = new CarTypeResponseDTO();
        when(carTypeService.updateCarType(1L, request)).thenReturn(response);

        ResponseEntity<CarTypeResponseDTO> result = adminCarTypeController.updateCarType(1L, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testDeleteCarType() {
        CarTypeResponseDTO response = new CarTypeResponseDTO();
        when(carTypeService.deleteCarType(1L)).thenReturn(response);

        ResponseEntity<CarTypeResponseDTO> result = adminCarTypeController.deleteCarType(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
