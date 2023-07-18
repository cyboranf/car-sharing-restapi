package com.example.carental.mapper;

import com.example.carental.model.CarStatus;
import com.example.carental.dto.carStatus.CarStatusRequestDTO;
import com.example.carental.dto.carStatus.CarStatusResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CarStatusMapper {

    public CarStatusResponseDTO toDTO(CarStatus carStatus) {
        CarStatusResponseDTO dto = new CarStatusResponseDTO();
        dto.setId(carStatus.getId());
        dto.setStatus(carStatus.getStatus());
        return dto;
    }

    public CarStatus fromDTO(CarStatusRequestDTO carStatusRequestDTO) {
        CarStatus carStatus = new CarStatus();
        carStatus.setStatus(carStatusRequestDTO.getStatus());
        return carStatus;
    }
}
