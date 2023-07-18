package com.example.carental.mapper;

import com.example.carental.model.Rent;
import com.example.carental.dto.rent.RentRequestDTO;
import com.example.carental.dto.rent.RentResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class RentMapper {

    public RentResponseDTO toDTO(Rent rent) {
        RentResponseDTO dto = new RentResponseDTO();
        dto.setId(rent.getId());
        dto.setStartDate(rent.getStartDate());
        dto.setEndDate(rent.getEndDate());
        dto.setCarId(rent.getCar().getId());
        return dto;
    }

    public Rent fromDTO(RentRequestDTO rentRequestDTO) {
        Rent rent = new Rent();
        rent.setStartDate(rentRequestDTO.getStartDate());
        rent.setEndDate(rentRequestDTO.getEndDate());
        return rent;
    }
}
