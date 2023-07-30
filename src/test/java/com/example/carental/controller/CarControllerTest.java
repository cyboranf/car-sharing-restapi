package com.example.carental.controller;

import com.example.carental.dto.car.CarRequestDTO;
import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.dto.carType.CarTypeResponseDTO;
import com.example.carental.service.CarService;
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

public class CarControllerTest {

    @InjectMocks
    private CarController carController;

    @Mock
    private CarService carService;

    @Mock
    private CarTypeService carTypeService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCar() {
        CarRequestDTO request = new CarRequestDTO();
        CarResponseDTO response = new CarResponseDTO();
        when(carService.addCar(request)).thenReturn(response);

        ResponseEntity<CarResponseDTO> result = carController.addCar(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testGetAllAvailableCars() {
        CarResponseDTO response1 = new CarResponseDTO();
        CarResponseDTO response2 = new CarResponseDTO();
        List<CarResponseDTO> responses = Arrays.asList(response1, response2);
        when(carService.getAllAvailableCars()).thenReturn(responses);

        ResponseEntity<List<CarResponseDTO>> result = carController.getAllAvailableCars();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }

    @Test
    public void testGetCarById() {
        CarResponseDTO response = new CarResponseDTO();
        when(carService.findCarById(1L)).thenReturn(response);

        ResponseEntity<CarResponseDTO> result = carController.getCarById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testUpdateCar() {
        CarRequestDTO request = new CarRequestDTO();
        CarResponseDTO response = new CarResponseDTO();
        when(carService.updateCar(1L, request)).thenReturn(response);

        ResponseEntity<CarResponseDTO> result = carController.updateCar(1L, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }


    @Test
    public void testGetCarTypeById() {
        CarTypeResponseDTO response = new CarTypeResponseDTO();
        when(carTypeService.getCarTypeById(1L)).thenReturn(response);

        ResponseEntity<CarTypeResponseDTO> result = carController.getCarTypeById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
