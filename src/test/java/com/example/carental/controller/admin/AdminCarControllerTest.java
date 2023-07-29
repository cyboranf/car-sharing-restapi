package com.example.carental.controller.admin;

import com.example.carental.controller.admin.AdminCarController;
import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.service.CarService;
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

public class AdminCarControllerTest {

    @InjectMocks
    private AdminCarController adminCarController;

    @Mock
    private CarService carService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCars() {
        CarResponseDTO response1 = new CarResponseDTO();
        CarResponseDTO response2 = new CarResponseDTO();
        List<CarResponseDTO> responses = Arrays.asList(response1, response2);
        when(carService.getAllCars()).thenReturn(responses);

        ResponseEntity<List<CarResponseDTO>> result = adminCarController.getAllCars();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }

    @Test
    public void testFindCarById() {
        CarResponseDTO response = new CarResponseDTO();
        when(carService.findCarById(1L)).thenReturn(response);

        ResponseEntity<CarResponseDTO> result = adminCarController.findCarById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testDeleteCar() {
        CarResponseDTO response = new CarResponseDTO();
        when(carService.deleteCar(1L)).thenReturn(response);

        ResponseEntity<CarResponseDTO> result = adminCarController.deleteCar(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
