package com.example.carental.dto.carType;

import com.example.carental.model.enums.CarTypeValue;
import lombok.Data;

@Data
public class CarTypeRequestDTO {
    private CarTypeValue type;
}
