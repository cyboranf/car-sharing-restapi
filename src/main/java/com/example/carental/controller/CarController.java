package com.example.carental.controller;

import com.example.carental.dto.car.CarRequestDTO;
import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * @param carRequestDTO
     * @return DTO of new Car
     */
    @PostMapping("")
    public ResponseEntity<CarResponseDTO> addCar(@Valid @RequestBody CarRequestDTO carRequestDTO) {
        return ResponseEntity.ok(carService.addCar(carRequestDTO));
    }

    /**
     * @return DTO of all cars
     */
    @GetMapping("")
    public ResponseEntity<List<CarResponseDTO>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    /**
     * @param carId
     * @return DTO of car with id = @param
     */
    @GetMapping("/{carId}")
    public ResponseEntity<CarResponseDTO> getCarById(@PathVariable Long carId) {
        return ResponseEntity.ok(carService.findCarById(carId));
    }

    /**
     * @param carId
     * @param carRequestDTO
     * @return DTO of updated Car with id = @param
     */
    @PutMapping("/{carId}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Long carId, CarRequestDTO carRequestDTO) {
        return ResponseEntity.ok(carService.updateCar(carId, carRequestDTO));
    }

    /**
     * @param carId
     * @return DTO of deleted Car
     */
    @DeleteMapping("/{carId}")
    public ResponseEntity deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.ok().build();
    }
}
