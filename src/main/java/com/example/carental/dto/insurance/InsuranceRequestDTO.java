package com.example.carental.dto.insurance;

import com.example.carental.model.enums.InsuranceProvider;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InsuranceRequestDTO {
    private String policyNumber;
    private InsuranceProvider provider;
    private LocalDate endDate;
    private Long carId;
}
