package com.example.carental.mapper;

import com.example.carental.model.*;
import com.example.carental.dto.car.*;
import com.example.carental.service.CarFeatureService;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CarMapper {
    private final CarFeatureService carFeatureService;

    public CarMapper(CarFeatureService carFeatureService) {
        this.carFeatureService = carFeatureService;
    }

    public CarResponseDTO toDTO(Car car) {
        CarResponseDTO dto = new CarResponseDTO();
        dto.setId(car.getId());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setDescription(car.getDescription());
        dto.setSeats(car.getSeats());
        dto.setOwnerId(car.getOwner().getId());
        dto.setStatusId(car.getStatus().getId());
        dto.setTypeId(car.getType().getId());
        dto.setManufacturerId(car.getManufacturer().getId());
        dto.setFeatureIds(car.getFeatures().stream()
                .map(CarFeature::getId)
                .collect(Collectors.toSet()));
        return dto;
    }

    public Car fromDTO(CarRequestDTO carRequestDTO) {
        Car car = new Car();
        car.setBrand(carRequestDTO.getBrand());
        car.setModel(carRequestDTO.getModel());
        car.setDescription(carRequestDTO.getDescription());
        car.setSeats(carRequestDTO.getSeats());

        Set<CarFeature> features = carRequestDTO.getFeatureIds().stream()
                .map(carFeatureService::findById) // replace with your service method
                .collect(Collectors.toSet());
        car.setFeatures(features);

        return car;
    }
}
