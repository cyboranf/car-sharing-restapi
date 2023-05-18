package com.example.carental.dto.paymentMethod;

import com.example.carental.model.enums.PaymentMethodValue;
import lombok.Data;

@Data
public class PaymentMethodResponseDTO {
    private Long id;
    private PaymentMethodValue method;
}
