package com.example.carental.validation;

import com.example.carental.dto.insurance.InsuranceRequestDTO;
import com.example.carental.exception.car.CarNotFoundException;
import com.example.carental.exception.insurance.InsuranceNotFoundException;
import com.example.carental.exception.insurance.InvalidInsuranceDateEndException;
import com.example.carental.exception.insurance.InvalidInsuranceProviderException;
import com.example.carental.exception.insurance.InvalidPolicyNumberException;
import com.example.carental.model.Insurance;
import com.example.carental.model.enums.InsuranceProvider;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.InsuranceRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InsuranceValidator {
    private final InsuranceRepository insuranceRepository;
    private final CarRepository carRepository;

    public InsuranceValidator(InsuranceRepository insuranceRepository, CarRepository carRepository) {
        this.insuranceRepository = insuranceRepository;
        this.carRepository = carRepository;
    }

    /**
     * @param insuranceRequestDTO
     * @return true/false or exception
     */
    public boolean validateInsuranceProvider(InsuranceRequestDTO insuranceRequestDTO) {
        try {
            InsuranceProvider.valueOf(insuranceRequestDTO.getProvider().name());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * @param insuranceRequestDTO
     */
    public void getInsuranceValidation(InsuranceRequestDTO insuranceRequestDTO) {
        if (!validateInsuranceProvider(insuranceRequestDTO)) {
            throw new InvalidInsuranceProviderException("Invalid insurance provider, try again.");
        }
        if (insuranceRequestDTO.getPolicyNumber() == null || insuranceRequestDTO.getPolicyNumber().length() != 8) {
            throw new InvalidPolicyNumberException("Wrong policy number, check it one more. Policy number should have 8 characters length.");
        }
        if (insuranceRequestDTO.getEndDate().isBefore(LocalDate.now())) {
            throw new InvalidInsuranceDateEndException("Wrong end date, check it and try again.");
        }
        carRepository.findById(insuranceRequestDTO.getCarId())
                .orElseThrow(() -> new CarNotFoundException("Can not add insurance to car with id = " + insuranceRequestDTO.getCarId() +
                        ", because car with this id doesnt exists."));
    }

    /**
     * @param insuranceId
     * @return insurance with id = insuranceId
     */
    public Insurance getByIdValidation(Long insuranceId) {
        return insuranceRepository.findById(insuranceId)
                .orElseThrow(() -> new InsuranceNotFoundException("Can not found Insurance with id = " + insuranceId));
    }

    /**
     * @param id
     * @param insuranceRequestDTO
     * @return Insurance to update
     */
    public Insurance updateInsuranceValidation(Long id, InsuranceRequestDTO insuranceRequestDTO) {
        Insurance insurance = getByIdValidation(id);
        getInsuranceValidation(insuranceRequestDTO);
        return insurance;
    }
}
