package com.example.carental.controller;

import com.example.carental.dto.payment.PaymentRequestDTO;
import com.example.carental.dto.payment.PaymentResponseDTO;
import com.example.carental.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
     * @return DTO of Payment
     */
    @GetMapping("/payments/{paymentId}/complete")
    public ResponseEntity<PaymentResponseDTO> completePayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.completePayment(paymentId));
    }

    /**
     * @param paymentId
     * @return DTO of payment details
     */
    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentDetails(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentDetails(paymentId));
    }

    /**
     * @param userId
     * @return DTO of all User with id = @param Payments.
     */
    @GetMapping("/payments/{userId}")
    public ResponseEntity<List<PaymentResponseDTO>> getUserPayments(@PathVariable Long userId) {
        List<PaymentResponseDTO> payments = paymentService.getUserPayments(userId);
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }
}
