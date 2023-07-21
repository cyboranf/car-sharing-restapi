package com.example.carental.validation;

import com.example.carental.dto.carType.CarTypeRequestDTO;
import com.example.carental.exception.carType.CarTypeNotFoundException;
import com.example.carental.exception.carType.InvalidCarTypeException;
import com.example.carental.model.CarType;
import com.example.carental.model.enums.InsuranceProvider;
import com.example.carental.repository.CarTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class CarTypeValidator {
    private final CarTypeRepository carTypeRepository;

    public CarTypeValidator(CarTypeRepository carTypeRepository) {
        this.carTypeRepository = carTypeRepository;
    }

    /**
     * @param carTypeRequestDTO
     * @return true/false or exception
     */
    public boolean validateCarTypeValue(CarTypeRequestDTO carTypeRequestDTO) {
        try {
            InsuranceProvider.valueOf(carTypeRequestDTO.getType().name());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * @param carTypeRequestDTO
     */
    public void validateCarTypeDoesNotExist(CarTypeRequestDTO carTypeRequestDTO) {
        validateCarTypeValue(carTypeRequestDTO);
        carTypeRepository.findByType(carTypeRequestDTO.getType())
                .ifPresent(carType -> {
                    throw new InvalidCarTypeException("CarType with type " + carTypeRequestDTO.getType() + " already exists.");
                });
    }

    /**
     * @param carTypeId
     * @return CarType with id = carTypeId or exception
     */
    public CarType getCarTypeByIdValidation(Long carTypeId) {
        return carTypeRepository.findById(carTypeId)
                .orElseThrow(() -> new CarTypeNotFoundException("Can not found Car Type with id = " + carTypeId));
    }

}
