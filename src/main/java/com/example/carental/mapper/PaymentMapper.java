package com.example.carental.mapper;

import com.example.carental.dto.payment.PaymentRequestDTO;
import com.example.carental.dto.payment.PaymentResponseDTO;
import com.example.carental.model.Payment;
import com.example.carental.model.Rent;
import com.example.carental.service.PaymentMethodService;
import com.example.carental.service.RentService;
import com.example.carental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    private final RentService rentService;
    private final UserService userService;
    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMapper(RentService rentService, UserService userService, PaymentMethodService paymentMethodService) {
        this.rentService = rentService;
        this.userService = userService;
        this.paymentMethodService = paymentMethodService;
    }

    public Payment toEntity(Long bookingId, PaymentRequestDTO paymentRequestDTO) {
        Payment payment = new Payment();
        payment.setStatus(paymentRequestDTO.getStatus());
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setMethod(paymentRequestDTO.getMethod());
        payment.setPaymentMethod(null); // We'll set it in PaymentService
        payment.setRent(rentService.getRentById2(bookingId));
        payment.setUser(userService.getUser2(paymentRequestDTO.getUserId()));
        return payment;
    }

    public PaymentResponseDTO toDTO(Payment payment) {
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        paymentResponseDTO.setId(payment.getId());
        paymentResponseDTO.setStatus(payment.getStatus());
        paymentResponseDTO.setAmount(payment.getAmount());
        paymentResponseDTO.setMethod(payment.getMethod());
        paymentResponseDTO.setPaymentMethodId(payment.getPaymentMethod() != null ? payment.getPaymentMethod().getId() : null);
        paymentResponseDTO.setRentId(payment.getRent().getId());
        paymentResponseDTO.setUserId(payment.getUser().getId());
        return paymentResponseDTO;
    }
}
