package com.example.carental.dto.booking;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequestDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long userId;
    private Long carId;
}
