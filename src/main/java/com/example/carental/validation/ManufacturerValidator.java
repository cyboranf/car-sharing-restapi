package com.example.carental.validation;

import com.example.carental.dto.manufacturer.ManufacturerRequestDTO;
import com.example.carental.exception.manufacturer.InvalidManufacturerException;
import com.example.carental.exception.manufacturer.ManufacturerNotFoundException;
import com.example.carental.model.Manufacturer;
import com.example.carental.repository.ManufacturerRepository;
import org.springframework.stereotype.Component;

@Component
public class ManufacturerValidator {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerValidator(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    /**
     * @param manufacturerRequestDTO
     */
    public void addManufacturerValidation(ManufacturerRequestDTO manufacturerRequestDTO) {
        if (manufacturerRequestDTO.getName() == null || manufacturerRequestDTO.getName().length() > 60) {
            throw new InvalidManufacturerException("Name of manufacturer can not be null and can not have more than 60 characters length.");
        }
    }

    /**
     * @param manufacturerId
     * @return Manufacturer with id = manufacturerId or exception
     */
    public Manufacturer getManufacturerByIdValidation(Long manufacturerId) {
        return manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException("Can not found manufacturer with id = " + manufacturerId));
    }
}
