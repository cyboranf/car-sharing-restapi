package com.example.carental.dto.carType;

import com.example.carental.model.enums.CarTypeValue;
import lombok.Data;

@Data
public class CarTypeResponseDTO {
    private Long id;
    private CarTypeValue type;
}
