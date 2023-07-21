package com.example.carental.dto.insurance;

import com.example.carental.model.enums.InsuranceProvider;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InsuranceResponseDTO {
    private Long id;
    private String policyNumber;
    private InsuranceProvider provider;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long carId;
}
