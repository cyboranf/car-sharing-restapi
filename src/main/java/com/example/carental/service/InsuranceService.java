package com.example.carental.service;

import com.example.carental.dto.insurance.InsuranceRequestDTO;
import com.example.carental.dto.insurance.InsuranceResponseDTO;
import com.example.carental.mapper.InsuranceMapper;
import com.example.carental.model.Car;
import com.example.carental.model.Insurance;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.InsuranceRepository;
import com.example.carental.validation.InsuranceValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final InsuranceMapper insuranceMapper;
    private final InsuranceValidator insuranceValidator;
    private final CarRepository carRepository;

    public InsuranceService(InsuranceRepository insuranceRepository, InsuranceMapper insuranceMapper, InsuranceValidator insuranceValidator, CarRepository carRepository) {
        this.insuranceRepository = insuranceRepository;
        this.insuranceMapper = insuranceMapper;
        this.insuranceValidator = insuranceValidator;
        this.carRepository = carRepository;
    }

    /**
     * @param insuranceRequestDTO
     * @return DTO of new insurance for car with id = insuranceRequestDTO.getId()
     */
    public InsuranceResponseDTO getInsurance(InsuranceRequestDTO insuranceRequestDTO) {
        insuranceValidator.getInsuranceValidation(insuranceRequestDTO);

        Insurance insurance = insuranceMapper.fromDTO(insuranceRequestDTO);

        insurance.setStartDate(LocalDate.now());
        insurance.setCar(carRepository.getById(insurance.getId()));

        Insurance savedInsurance = insuranceRepository.save(insurance);

        return insuranceMapper.toDTO(savedInsurance);
    }

    /**
     * @return DTO of all Insurance
     */
    public List<InsuranceResponseDTO> getAllInsurances() {
        return insuranceRepository.findAll().stream()
                .map(insuranceMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param insuranceId
     * @return DTO of insurance with id = insuranceId
     */
    public InsuranceResponseDTO getInsuranceById(Long insuranceId) {
        Insurance insurance = insuranceValidator.getByIdValidation(insuranceId);

        return insuranceMapper.toDTO(insurance);
    }

    /**
     * @param id
     * @param insuranceRequestDTO
     * @return DTO of updated Insurance
     */
    public InsuranceResponseDTO updateInsurance(Long id, InsuranceRequestDTO insuranceRequestDTO) {
        Insurance insurance = insuranceValidator.updateInsuranceValidation(id, insuranceRequestDTO);

        insurance.setStartDate(LocalDate.now());
        insurance.setCar(carRepository.getById(insurance.getId()));

        Insurance savedInsurance = insuranceRepository.save(insurance);

        return insuranceMapper.toDTO(savedInsurance);
    }

    /**
     * @param id
     * @return DTO of deleted Insurance
     */
    public InsuranceResponseDTO deleteInsurance(Long id) {
        Insurance insurance = insuranceValidator.getByIdValidation(id);
        insuranceRepository.delete(insurance);
        return insuranceMapper.toDTO(insurance);
    }
}
