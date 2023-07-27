package com.example.carental.controller.admin;

import com.example.carental.dto.carStatus.CarStatusRequestDTO;
import com.example.carental.dto.carStatus.CarStatusResponseDTO;
import com.example.carental.service.CarStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/carstatus")
public class AdminCarStatusController {
    private final CarStatusService carStatusService;

    public AdminCarStatusController(CarStatusService carStatusService) {
        this.carStatusService = carStatusService;
    }

    /**
     * @param carStatusRequestDTO
     * @return DTO of new CarStatus
     */
    @PostMapping
    public ResponseEntity<CarStatusResponseDTO> createCarStatus(@RequestBody CarStatusRequestDTO carStatusRequestDTO) {
        CarStatusResponseDTO carStatus = carStatusService.createCarStatus(carStatusRequestDTO);
        return new ResponseEntity<>(carStatus, HttpStatus.CREATED);
    }

    /**
     * @return List of all CarStatus in car-sharing offer
     */
    @GetMapping
    public ResponseEntity<List<CarStatusResponseDTO>> getAllCarStatuses() {
        List<CarStatusResponseDTO> carStatuses = carStatusService.getAllCarStatuses();
        return new ResponseEntity<>(carStatuses, HttpStatus.OK);
    }

    /**
     * @param id
     * @param carStatusRequestDTO
     * @return DTO of updated CarStatus with id = @param
     */
    @PutMapping("/{id}")
    public ResponseEntity<CarStatusResponseDTO> updateCarStatus(@PathVariable Long id, @RequestBody CarStatusRequestDTO carStatusRequestDTO) {
        CarStatusResponseDTO carStatus = carStatusService.updateCarStatus(id, carStatusRequestDTO);
        return new ResponseEntity<>(carStatus, HttpStatus.OK);
    }

    /**
     * @param id
     * @return DTO of deleted CarStatus from car-sharing
     *                            |
     *                     with id = @param
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CarStatusResponseDTO> deleteCarStatus(@PathVariable Long id) {
        CarStatusResponseDTO carStatus = carStatusService.deleteCarStatus(id);
        return new ResponseEntity<>(carStatus, HttpStatus.OK);
    }
}
