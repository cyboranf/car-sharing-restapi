package com.example.carental.service;

import com.example.carental.dto.car.CarRequestDTO;
import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.exception.ResourceNotFoundException;
import com.example.carental.mapper.CarMapper;
import com.example.carental.model.Car;
import com.example.carental.repository.CarRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarResponseDTO addCar(CarRequestDTO carRequestDTO) {
        Car car = CarMapper.mapCarRequestDTOToCar(carRequestDTO);
        Car savedCar = carRepository.save(car);
        return CarMapper.mapCarToCarResponseDTO(savedCar);
    }

    public List<CarResponseDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(CarMapper::mapCarToCarResponseDTO)
                .collect(Collectors.toList());
    }

    public CarResponseDTO updateCar(Long carId, CarRequestDTO carRequestDTO) {
        Car car = carRepository.findById(carId).orElse(null);
        if (car != null) {
            CarMapper.updateCarFields(car, carRequestDTO);
            Car updatedCar = carRepository.save(car);
            return CarMapper.mapCarToCarResponseDTO(updatedCar);
        }
        return null;
    }

    public CarResponseDTO getCarById(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with ID: " + carId));
        return CarMapper.mapCarToCarResponseDTO(car);
    }

    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }

}
