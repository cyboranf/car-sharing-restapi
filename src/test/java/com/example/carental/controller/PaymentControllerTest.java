package com.example.carental.controller;

import com.example.carental.dto.payment.PaymentRequestDTO;
import com.example.carental.dto.payment.PaymentResponseDTO;
import com.example.carental.service.PaymentService;
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

public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMakePayment() {
        PaymentRequestDTO request = new PaymentRequestDTO();
        PaymentResponseDTO response = new PaymentResponseDTO();
        when(paymentService.makePayment(1L, request)).thenReturn(response);

        ResponseEntity<PaymentResponseDTO> result = paymentController.makePayment(1L, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testCompletePayment() {
        PaymentResponseDTO response = new PaymentResponseDTO();
        when(paymentService.completePayment(1L)).thenReturn(response);

        ResponseEntity<PaymentResponseDTO> result = paymentController.completePayment(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testGetPaymentDetails() {
        PaymentResponseDTO response = new PaymentResponseDTO();
        when(paymentService.getPaymentDetails(1L)).thenReturn(response);

        ResponseEntity<PaymentResponseDTO> result = paymentController.getPaymentDetails(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testGetUserPayments() {
        PaymentResponseDTO response1 = new PaymentResponseDTO();
        PaymentResponseDTO response2 = new PaymentResponseDTO();
        List<PaymentResponseDTO> responses = Arrays.asList(response1, response2);
        when(paymentService.getUserPayments(1L)).thenReturn(responses);

        ResponseEntity<List<PaymentResponseDTO>> result = paymentController.getUserPayments(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }
}
