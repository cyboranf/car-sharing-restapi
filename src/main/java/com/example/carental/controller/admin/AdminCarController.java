package com.example.carental.controller.admin;

import com.example.carental.dto.car.CarResponseDTO;
import com.example.carental.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/car")
public class AdminCarController {
    private final CarService carService;

    public AdminCarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * @return List of all cars in car-sharing system
     */
    @GetMapping
    public ResponseEntity<List<CarResponseDTO>> getAllCars() {
        List<CarResponseDTO> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /**
     * @param id
     * @return DTO of Car with id = @param
     */
    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDTO> findCarById(@PathVariable Long id) {
        CarResponseDTO car = carService.findCarById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /**
     * @param id
     * @return DTO of deleted Car with id = @param
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CarResponseDTO> deleteCar(@PathVariable Long id) {
        CarResponseDTO car = carService.deleteCar(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
}
