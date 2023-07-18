package com.example.carental.mapper;

import com.example.carental.model.PaymentMethod;
import com.example.carental.dto.paymentMethod.PaymentMethodRequestDTO;
import com.example.carental.dto.paymentMethod.PaymentMethodResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodMapper {

    public PaymentMethodResponseDTO toDTO(PaymentMethod paymentMethod) {
        PaymentMethodResponseDTO dto = new PaymentMethodResponseDTO();
        dto.setId(paymentMethod.getId());
        dto.setMethod(paymentMethod.getMethod());
        return dto;
    }

    public PaymentMethod fromDTO(PaymentMethodRequestDTO paymentMethodRequestDTO) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setMethod(paymentMethodRequestDTO.getMethod());
        return paymentMethod;
    }
}
