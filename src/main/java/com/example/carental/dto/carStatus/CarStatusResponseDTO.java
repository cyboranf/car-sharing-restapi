package com.example.carental.dto.carStatus;

import com.example.carental.model.enums.CarStatusValue;
import lombok.Data;

@Data
public class CarStatusResponseDTO {
    private Long id;
    private CarStatusValue status;
}
