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

    @PostMapping("")
    public ResponseEntity<CarResponseDTO> addCar(@Valid @RequestBody CarRequestDTO carRequestDTO) {
        return ResponseEntity.ok(carService.addCar(carRequestDTO));
    }

    @GetMapping("")
    public ResponseEntity<List<CarResponseDTO>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarResponseDTO> getCarById(@PathVariable Long carId) {
        return ResponseEntity.ok(carService.getCarById(carId));
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable Long carId, CarRequestDTO carRequestDTO) {
        return ResponseEntity.ok(carService.updateCar(carId, carRequestDTO));
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.ok().build();
    }
}
