package com.example.carental.dto.paymentMethod;

import com.example.carental.model.enums.PaymentMethodValue;
import lombok.Data;

@Data
public class PaymentMethodRequestDTO {
    private PaymentMethodValue method;
}
