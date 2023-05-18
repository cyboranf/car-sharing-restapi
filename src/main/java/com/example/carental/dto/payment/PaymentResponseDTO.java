package com.example.carental.dto.payment;

import com.example.carental.model.enums.PaymentMethodValue;
import com.example.carental.model.enums.PaymentStatus;
import lombok.Data;

@Data
public class PaymentResponseDTO {
    private Long id;
    private PaymentStatus status;
    private Double amount;
    private PaymentMethodValue method;
    private Long paymentMethodId;
    private Long rentId;
    private Long userId;
}
