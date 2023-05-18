package com.example.carental.dto.insurance;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InsuranceResponseDTO {
    private Long id;
    private String policyNumber;
    private String provider;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long carId;
}
