package com.example.carental.controller.admin;

import com.example.carental.dto.address.AddressRequestDTO;
import com.example.carental.dto.address.AddressResponseDTO;
import com.example.carental.service.AddressService;
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

public class AdminAddressControllerTest {

    @InjectMocks
    private AdminAddressController adminAddressController;

    @Mock
    private AddressService addressService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAddress() {
        AddressRequestDTO request = new AddressRequestDTO();
        AddressResponseDTO response = new AddressResponseDTO();
        when(addressService.createAddress(request)).thenReturn(response);

        ResponseEntity<AddressResponseDTO> result = adminAddressController.createAddress(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testGetAddress() {
        AddressResponseDTO response = new AddressResponseDTO();
        when(addressService.getAddress(1L)).thenReturn(response);

        ResponseEntity<AddressResponseDTO> result = adminAddressController.getAddress(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testGetAllAddresses() {
        AddressResponseDTO response1 = new AddressResponseDTO();
        AddressResponseDTO response2 = new AddressResponseDTO();
        List<AddressResponseDTO> responses = Arrays.asList(response1, response2);
        when(addressService.getAllAddresses()).thenReturn(responses);

        ResponseEntity<List<AddressResponseDTO>> result = adminAddressController.getAllAddresses();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }

    @Test
    public void testUpdateAddress() {
        AddressRequestDTO request = new AddressRequestDTO();
        AddressResponseDTO response = new AddressResponseDTO();
        when(addressService.updateAddress(1L, request)).thenReturn(response);

        ResponseEntity<AddressResponseDTO> result = adminAddressController.updateAddress(1L, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testDeleteAddress() {
        AddressResponseDTO response = new AddressResponseDTO();
        when(addressService.deleteAddress(1L)).thenReturn(response);

        ResponseEntity<AddressResponseDTO> result = adminAddressController.deleteAddress(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
