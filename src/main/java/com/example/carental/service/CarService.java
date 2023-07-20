package com.example.carental.service;

import com.example.carental.dto.car.CarRequestDTO;
import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.mapper.CarMapper;
import com.example.carental.model.Car;
import com.example.carental.model.enums.CarStatusValue;
import com.example.carental.repository.CarRepository;
import com.example.carental.validation.CarValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarService {
    private final CarRepository carRepository;
    private final CarValidator carValidator;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarValidator carValidator, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carValidator = carValidator;
        this.carMapper = carMapper;
    }

    /**
     * @param carRequestDTO
     * @return DTO of new Car to rent
     */
    public CarResponseDTO addCar(CarRequestDTO carRequestDTO) {
        carValidator.addCarValidation(carRequestDTO);

        Car car = carMapper.fromDTO(carRequestDTO);

        Car savedCar = carRepository.save(car);

        return carMapper.toDTO(savedCar);
    }

    /**
     * @return dto's of all cars
     */
    public List<CarResponseDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @return dto's of all available cars
     */
    public List<CarResponseDTO> getAllAvailableCars() {
        List<Car> availableCars = carRepository.findByStatus_Status(CarStatusValue.AVAILABLE);
        return availableCars.stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param carId
     * @param carRequestDTO
     * @return response of updatedCar
     */
    public CarResponseDTO updateCar(Long carId, CarRequestDTO carRequestDTO) {
        carValidator.updateCarValidation(carId, carRequestDTO);

        Car carToUpdate = carMapper.fromDTO(carRequestDTO);

        Car savedCar = carRepository.save(carToUpdate);

        return carMapper.toDTO(savedCar);
    }

    /**
     * @param carId
     * @return dto of car with id = carId
     */
    public CarResponseDTO findCarById(Long carId) {
        return carMapper.toDTO(carValidator.getByIdValidation(carId));
    }

    /**
     * @param carId
     * @return DTO of deletedCar
     */
    public CarResponseDTO deleteCar(Long carId) {
        Car deletedCar = carValidator.getByIdValidation(carId);
        carRepository.delete(carValidator.getByIdValidation(carId));
        return carMapper.toDTO(deletedCar);
    }
}
