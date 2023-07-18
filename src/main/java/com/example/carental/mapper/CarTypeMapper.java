package com.example.carental.mapper;

import com.example.carental.model.CarType;
import com.example.carental.dto.carType.CarTypeRequestDTO;
import com.example.carental.dto.carType.CarTypeResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CarTypeMapper {

    public CarTypeResponseDTO toDTO(CarType carType) {
        CarTypeResponseDTO dto = new CarTypeResponseDTO();
        dto.setId(carType.getId());
        dto.setType(carType.getType());
        return dto;
    }

    public CarType fromDTO(CarTypeRequestDTO carTypeRequestDTO) {
        CarType carType = new CarType();
        carType.setType(carTypeRequestDTO.getType());
        return carType;
    }
}
