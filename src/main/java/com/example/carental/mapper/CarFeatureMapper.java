package com.example.carental.mapper;

import com.example.carental.model.CarFeature;
import com.example.carental.dto.carFeature.CarFeatureRequestDTO;
import com.example.carental.dto.carFeature.CarFeatureResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CarFeatureMapper {

    public CarFeatureResponseDTO toDTO(CarFeature carFeature) {
        CarFeatureResponseDTO dto = new CarFeatureResponseDTO();
        dto.setId(carFeature.getId());
        dto.setName(carFeature.getName());
        return dto;
    }

    public CarFeature fromDTO(CarFeatureRequestDTO carFeatureRequestDTO) {
        CarFeature carFeature = new CarFeature();
        carFeature.setName(carFeatureRequestDTO.getName());
        return carFeature;
    }
}
