package com.example.carental.mapper;

import com.example.carental.model.Payment;
import com.example.carental.dto.payment.PaymentRequestDTO;
import com.example.carental.dto.payment.PaymentResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponseDTO toDTO(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(payment.getId());
        dto.setStatus(payment.getStatus());
        dto.setAmount(payment.getAmount());
        dto.setMethod(payment.getMethod());
        dto.setPaymentMethodId(payment.getPaymentMethod().getId());
        dto.setRentId(payment.getRent().getId());
        dto.setUserId(payment.getUser().getId());
        return dto;
    }

    public Payment fromDTO(PaymentRequestDTO paymentRequestDTO) {
        Payment payment = new Payment();
        payment.setStatus(paymentRequestDTO.getStatus());
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setMethod(paymentRequestDTO.getMethod());
        return payment;
    }
}
