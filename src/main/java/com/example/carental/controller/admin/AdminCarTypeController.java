package com.example.carental.controller.admin;

import com.example.carental.dto.carType.CarTypeRequestDTO;
import com.example.carental.dto.carType.CarTypeResponseDTO;
import com.example.carental.service.CarTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/cartype")
public class AdminCarTypeController {
    private final CarTypeService carTypeService;

    public AdminCarTypeController(CarTypeService carTypeService) {
        this.carTypeService = carTypeService;
    }

    /**
     * @param carTypeRequestDTO
     * @return add new CarType to offer of car-sharing
     */
    @PostMapping
    public ResponseEntity<CarTypeResponseDTO> createCarType(@RequestBody CarTypeRequestDTO carTypeRequestDTO) {
        CarTypeResponseDTO carType = carTypeService.createCarType(carTypeRequestDTO);
        return new ResponseEntity<>(carType, HttpStatus.CREATED);
    }

    /**
     * @return All Types of Cars available to rent and share
     */
    @GetMapping
    public ResponseEntity<List<CarTypeResponseDTO>> getAllCarTypes() {
        List<CarTypeResponseDTO> carTypes = carTypeService.getAllCarTypes();
        return new ResponseEntity<>(carTypes, HttpStatus.OK);
    }

    /**
     * @param id
     * @param carTypeRequestDTO
     * @return DTO of updated CarType
     */
    @PutMapping("/{id}")
    public ResponseEntity<CarTypeResponseDTO> updateCarType(@PathVariable Long id, @RequestBody CarTypeRequestDTO carTypeRequestDTO) {
        CarTypeResponseDTO carType = carTypeService.updateCarType(id, carTypeRequestDTO);
        return new ResponseEntity<>(carType, HttpStatus.OK);
    }

    /**
     * @param id
     * @return DTO of deleted CarType
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CarTypeResponseDTO> deleteCarType(@PathVariable Long id) {
        CarTypeResponseDTO carType = carTypeService.deleteCarType(id);
        return new ResponseEntity<>(carType, HttpStatus.OK);
    }
}
