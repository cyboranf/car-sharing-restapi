package com.example.carental.mapper;

import com.example.carental.dto.rent.RentRequestDTO;
import com.example.carental.dto.rent.RentResponseDTO;
import com.example.carental.model.Rent;
import com.example.carental.service.CarService;
import org.springframework.stereotype.Component;

@Component
public class RentMapper {
    private final CarService carService;

    public RentMapper(CarService carService) {
        this.carService = carService;
    }

    public Rent toEntity(RentRequestDTO rentRequestDTO) {
        Rent rent = new Rent();
        rent.setStartDate(rentRequestDTO.getStartDate());
        rent.setEndDate(rentRequestDTO.getEndDate());
        rent.setCar(carService.findCarById(rentRequestDTO.getCarId()));
        return rent;
    }

    public RentResponseDTO toDTO(Rent rent) {
        RentResponseDTO rentResponseDTO = new RentResponseDTO();
        rentResponseDTO.setId(rent.getId());
        rentResponseDTO.setStartDate(rent.getStartDate());
        rentResponseDTO.setEndDate(rent.getEndDate());
        rentResponseDTO.setCarId(rent.getCar().getId());
        return rentResponseDTO;
    }
}
