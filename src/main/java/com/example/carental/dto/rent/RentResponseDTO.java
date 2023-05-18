package com.example.carental.dto.rent;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentResponseDTO {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long carId;
}
