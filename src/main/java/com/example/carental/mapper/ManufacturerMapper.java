package com.example.carental.mapper;

import com.example.carental.model.Manufacturer;
import com.example.carental.dto.manufacturer.ManufacturerRequestDTO;
import com.example.carental.dto.manufacturer.ManufacturerResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerMapper {

    public ManufacturerResponseDTO toDTO(Manufacturer manufacturer) {
        ManufacturerResponseDTO dto = new ManufacturerResponseDTO();
        dto.setId(manufacturer.getId());
        dto.setName(manufacturer.getName());
        return dto;
    }

    public Manufacturer fromDTO(ManufacturerRequestDTO manufacturerRequestDTO) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(manufacturerRequestDTO.getName());
        return manufacturer;
    }
}
