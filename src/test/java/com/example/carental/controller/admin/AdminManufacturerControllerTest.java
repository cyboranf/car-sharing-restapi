package com.example.carental.controller.admin;

import com.example.carental.controller.admin.AdminManufacturerController;
import com.example.carental.dto.manufacturer.ManufacturerResponseDTO;
import com.example.carental.service.ManufacturerService;
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

public class AdminManufacturerControllerTest {

    @InjectMocks
    private AdminManufacturerController adminManufacturerController;

    @Mock
    private ManufacturerService manufacturerService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllManufacturers() {
        ManufacturerResponseDTO response1 = new ManufacturerResponseDTO();
        ManufacturerResponseDTO response2 = new ManufacturerResponseDTO();
        List<ManufacturerResponseDTO> responses = Arrays.asList(response1, response2);
        when(manufacturerService.getAllManufacturers()).thenReturn(responses);

        ResponseEntity<List<ManufacturerResponseDTO>> result = adminManufacturerController.getAllManufacturers();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }

    @Test
    public void testGetManufacturerById() {
        ManufacturerResponseDTO response = new ManufacturerResponseDTO();
        when(manufacturerService.getManufacturerById(1L)).thenReturn(response);

        ResponseEntity<ManufacturerResponseDTO> result = adminManufacturerController.getManufactureById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testDeleteManufacturer() {
        ManufacturerResponseDTO response = new ManufacturerResponseDTO();
        when(manufacturerService.deleteManufacturer(1L)).thenReturn(response);

        ResponseEntity<ManufacturerResponseDTO> result = adminManufacturerController.deleteManufacturer(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
