package com.example.carental.controller.admin;

import com.example.carental.dto.insurance.InsuranceResponseDTO;
import com.example.carental.service.InsuranceService;
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

public class AdminInsuranceControllerTest {

    @InjectMocks
    private AdminInsuranceController adminInsuranceController;

    @Mock
    private InsuranceService insuranceService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllInsurance() {
        InsuranceResponseDTO response1 = new InsuranceResponseDTO();
        InsuranceResponseDTO response2 = new InsuranceResponseDTO();
        List<InsuranceResponseDTO> responses = Arrays.asList(response1, response2);
        when(insuranceService.getAllInsurances()).thenReturn(responses);

        ResponseEntity<List<InsuranceResponseDTO>> result = adminInsuranceController.getAllInsurance();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }

    @Test
    public void testGetInsuranceById() {
        InsuranceResponseDTO response = new InsuranceResponseDTO();
        when(insuranceService.getInsuranceById(1L)).thenReturn(response);

        ResponseEntity<InsuranceResponseDTO> result = adminInsuranceController.getInsuranceById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testDeleteInsurance() {
        InsuranceResponseDTO response = new InsuranceResponseDTO();
        when(insuranceService.deleteInsurance(1L)).thenReturn(response);

        ResponseEntity<InsuranceResponseDTO> result = adminInsuranceController.deleteInsurance(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
