package com.example.carental.controller;

import com.example.carental.dto.payment.PaymentRequestDTO;
import com.example.carental.dto.payment.PaymentResponseDTO;
import com.example.carental.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * @param bookingId
     * @param paymentRequestDTO
     * @return DTO of Payment
     */
    @PostMapping("/bookings/{bookingId}/payments")
    public ResponseEntity<PaymentResponseDTO> makePayment(
            @PathVariable Long bookingId,
            @RequestBody PaymentRequestDTO paymentRequestDTO) {
        return ResponseEntity.ok(paymentService.makePayment(bookingId, paymentRequestDTO));
    }

    /**
     * @param paymentId
     * @return DTO of payment details
     */
    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentDetails(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentDetails(paymentId));
    }
}
