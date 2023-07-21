package com.example.carental.mapper;

import com.example.carental.model.Insurance;
import com.example.carental.dto.insurance.InsuranceRequestDTO;
import com.example.carental.dto.insurance.InsuranceResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class InsuranceMapper {

    public InsuranceResponseDTO toDTO(Insurance insurance) {
        InsuranceResponseDTO dto = new InsuranceResponseDTO();
        dto.setId(insurance.getId());
        dto.setPolicyNumber(insurance.getPolicyNumber());
        dto.setProvider(insurance.getProvider());
        dto.setStartDate(insurance.getStartDate());
        dto.setEndDate(insurance.getEndDate());
        dto.setCarId(insurance.getCar().getId());
        return dto;
    }

    public Insurance fromDTO(InsuranceRequestDTO insuranceRequestDTO) {
        Insurance insurance = new Insurance();
        insurance.setPolicyNumber(insuranceRequestDTO.getPolicyNumber());
        insurance.setProvider(insuranceRequestDTO.getProvider());
        insurance.setEndDate(insuranceRequestDTO.getEndDate());
        return insurance;
    }
}
