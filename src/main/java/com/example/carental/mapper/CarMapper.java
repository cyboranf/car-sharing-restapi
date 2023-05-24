package com.example.carental.mapper;

import com.example.carental.dto.car.CarRequestDTO;
import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.model.Car;
import com.example.carental.model.CarFeature;

import java.util.stream.Collectors;

public class CarMapper {
    public static Car mapCarRequestDTOToCar(CarRequestDTO carRequestDTO) {
        Car car = new Car();
        car.setBrand(carRequestDTO.getBrand());
        car.setModel(carRequestDTO.getModel());
        car.setDescription(carRequestDTO.getDescription());
        car.setSeats(carRequestDTO.getSeats());
        return car;
    }

    public static CarResponseDTO mapCarToCarResponseDTO(Car car) {
        CarResponseDTO carResponseDTO = new CarResponseDTO();
        carResponseDTO.setId(car.getId());
        carResponseDTO.setModel(car.getModel());
        carResponseDTO.setDescription(car.getDescription());
        carResponseDTO.setSeats(car.getSeats());
        carResponseDTO.setOwnerId(car.getOwner() != null ? car.getOwner().getId() : null);
        carResponseDTO.setStatusId(car.getStatus() != null ? car.getStatus().getId() : null);
        carResponseDTO.setTypeId(car.getType() != null ? car.getType().getId() : null);
        carResponseDTO.setManufacturerId(car.getManufacturer() != null ? car.getManufacturer().getId() : null);
        carResponseDTO.setFeatureIds(car.getFeatures() != null ? car.getFeatures().stream().map(CarFeature::getId).collect(Collectors.toSet()) : null);
        return carResponseDTO;
    }

    public static void updateCarFields(Car car, CarRequestDTO carRequestDTO) {
        car.setBrand(carRequestDTO.getBrand());
        car.setModel(carRequestDTO.getModel());
        car.setDescription(carRequestDTO.getDescription());
        car.setSeats(carRequestDTO.getSeats());
    }
}
