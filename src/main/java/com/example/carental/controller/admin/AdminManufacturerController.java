package com.example.carental.controller.admin;

import com.example.carental.dto.manufacturer.ManufacturerRequestDTO;
import com.example.carental.dto.manufacturer.ManufacturerResponseDTO;
import com.example.carental.service.ManufacturerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminManufacturerController {
    private final ManufacturerService manufacturerService;

    public AdminManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    /**
     * @param manufacturerRequestDTO
     * @return New manufacturer created by admin
     */
    @PostMapping
    public ResponseEntity<ManufacturerResponseDTO> addManufacturer(@RequestBody ManufacturerRequestDTO manufacturerRequestDTO) {
        ManufacturerResponseDTO manufacturer = manufacturerService.addManufacturer(manufacturerRequestDTO);
        return new ResponseEntity<>(manufacturer, HttpStatus.CREATED);
    }

    /**
     * @return All manufacturers
     */
    @GetMapping
    public ResponseEntity<List<ManufacturerResponseDTO>> getAllManufacturers() {
        List<ManufacturerResponseDTO> allManufacturers = manufacturerService.getAllManufacturers();
        return new ResponseEntity<>(allManufacturers, HttpStatus.OK);
    }

    /**
     * @param id
     * @return DTO of manufacturer with id = @param
     */
    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerResponseDTO> getManufactureById(@PathVariable Long id) {
        ManufacturerResponseDTO manufacturer = manufacturerService.getManufacturerById(id);
        return new ResponseEntity<>(manufacturer, HttpStatus.OK);
    }

    /**
     * @param id
     * @param manufacturerRequestDTO
     * @return DTO of updated Manufacturer with id = @param
     */
    @PutMapping("/{id}")
    public ResponseEntity<ManufacturerResponseDTO> updateManufacturer(@PathVariable Long id, ManufacturerRequestDTO manufacturerRequestDTO) {
        ManufacturerResponseDTO updatedManufacturer = manufacturerService.updateManufacturer(id, manufacturerRequestDTO);
        return new ResponseEntity<>(updatedManufacturer, HttpStatus.OK);
    }

    /**
     * @param id
     * @return DTO of deleted Manufacturer with id = @param
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ManufacturerResponseDTO> deleteManufacturer(@PathVariable Long id) {
        ManufacturerResponseDTO deletedManufacturer = manufacturerService.deleteManufacturer(id);
        return new ResponseEntity<>(deletedManufacturer, HttpStatus.OK);
    }
}
