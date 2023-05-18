package com.example.carental.dto.carStatus;

import com.example.carental.model.enums.CarStatusValue;
import lombok.Data;

@Data
public class CarStatusRequestDTO {
    private CarStatusValue status;
}
