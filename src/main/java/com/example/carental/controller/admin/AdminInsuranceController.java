package com.example.carental.controller.admin;

import com.example.carental.dto.insurance.InsuranceRequestDTO;
import com.example.carental.dto.insurance.InsuranceResponseDTO;
import com.example.carental.service.InsuranceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminInsuranceController {
    private final InsuranceService insuranceService;

    public AdminInsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    /**
     * @param insuranceRequestDTO
     *        add new insurance by ADMIN to offer
     * @return DTO of new insurance
     */
    @PostMapping
    public ResponseEntity<InsuranceResponseDTO> createInsurance(InsuranceRequestDTO insuranceRequestDTO) {
        InsuranceResponseDTO insurance = insuranceService.getInsurance(insuranceRequestDTO);
        return new ResponseEntity<>(insurance, HttpStatus.CREATED);
    }

    /**
     * @return All insurance in car-sharing offer
     */
    @GetMapping
    public ResponseEntity<List<InsuranceResponseDTO>> getAllInsurance() {
        List<InsuranceResponseDTO> insurances = insuranceService.getAllInsurances();
        return new ResponseEntity<>(insurances, HttpStatus.OK);
    }

    /**
     * @param id
     * @return DTO of insurance with id = @param
     */
    @GetMapping("/{id}")
    public ResponseEntity<InsuranceResponseDTO> getInsuranceById(@PathVariable Long id) {
        InsuranceResponseDTO insurance = insuranceService.getInsuranceById(id);
        return new ResponseEntity<>(insurance, HttpStatus.OK);
    }

    /**
     * @param id
     * @param insuranceRequestDTO
     * @return DTO of edited Insurance with id = @param
     */
    @PutMapping("/{id}")
    public ResponseEntity<InsuranceResponseDTO> updateInsurance(@PathVariable Long id, @RequestBody InsuranceRequestDTO insuranceRequestDTO) {
        InsuranceResponseDTO insurance = insuranceService.updateInsurance(id, insuranceRequestDTO);
        return new ResponseEntity<>(insurance, HttpStatus.OK);
    }

    /**
     * @param id
     * @return DTO of deleted insurance from offer
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<InsuranceResponseDTO> deleteInsurance(@PathVariable Long id) {
        InsuranceResponseDTO insurance = insuranceService.deleteInsurance(id);
        return new ResponseEntity<>(insurance, HttpStatus.OK);
    }
}
