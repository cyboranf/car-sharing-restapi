package com.example.carental.validation;

import com.example.carental.dto.carStatus.CarStatusRequestDTO;
import com.example.carental.exception.carStatus.CarStatusNotFoundException;
import com.example.carental.model.CarStatus;
import com.example.carental.model.enums.InsuranceProvider;
import com.example.carental.repository.CarStatusRepository;
import org.springframework.stereotype.Component;

@Component
public class CarStatusValidator {
    private final CarStatusRepository carStatusRepository;

    public CarStatusValidator(CarStatusRepository carStatusRepository) {
        this.carStatusRepository = carStatusRepository;
    }

    /**
     * @param carStatusRequestDTO
     * @return true/false or exception
     */
    public boolean validateCarStatusValue(CarStatusRequestDTO carStatusRequestDTO) {
        try {
            InsuranceProvider.valueOf(carStatusRequestDTO.getStatus().name());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * @param carStatusRequestDTO
     */
    public void createCarStatusValidation(CarStatusRequestDTO carStatusRequestDTO) {
        validateCarStatusValue(carStatusRequestDTO);
    }

    /**
     * @param carStatusId
     * @return validated CarStatus with id = carStatusID
     */
    public CarStatus getByIdValidation(Long carStatusId) {
        return carStatusRepository.findById(carStatusId)
                .orElseThrow(() -> new CarStatusNotFoundException("Can not found Car Status with id = " + carStatusId));
    }
}
