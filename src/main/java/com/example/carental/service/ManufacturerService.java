package com.example.carental.service;

import com.example.carental.dto.manufacturer.ManufacturerRequestDTO;
import com.example.carental.dto.manufacturer.ManufacturerResponseDTO;
import com.example.carental.mapper.ManufacturerMapper;
import com.example.carental.model.Manufacturer;
import com.example.carental.repository.ManufacturerRepository;
import com.example.carental.validation.ManufacturerValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerMapper manufacturerMapper;
    private final ManufacturerValidator manufacturerValidator;

    public ManufacturerService(ManufacturerRepository manufacturerRepository, ManufacturerMapper manufacturerMapper, ManufacturerValidator manufacturerValidator) {
        this.manufacturerRepository = manufacturerRepository;
        this.manufacturerMapper = manufacturerMapper;
        this.manufacturerValidator = manufacturerValidator;
    }

    /**
     * @param manufacturerRequestDTO
     * @return DTO of new manufacturer
     */
    public ManufacturerResponseDTO addManufacturer(ManufacturerRequestDTO manufacturerRequestDTO) {
        manufacturerValidator.addManufacturerValidation(manufacturerRequestDTO);

        Manufacturer manufacturer = manufacturerMapper.fromDTO(manufacturerRequestDTO);

        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);

        return manufacturerMapper.toDTO(savedManufacturer);
    }

    /**
     * @return List of DTO of all manufacturers
     */
    public List<ManufacturerResponseDTO> getAllManufacturers() {
        return manufacturerRepository.findAll().stream()
                .map(manufacturerMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param manufacturerId
     * @return DTO of manufacturer with id = manufacturerId
     */
    public ManufacturerResponseDTO getManufacturerById(Long manufacturerId) {
        Manufacturer manufacturer = manufacturerValidator.getManufacturerByIdValidation(manufacturerId);
        return manufacturerMapper.toDTO(manufacturer);
    }

    /**
     * @param manufacturerId
     * @param manufacturerRequestDTO
     * @return DTO of updated manufacturer with id = manufacturerId
     */
    public ManufacturerResponseDTO updateManufacturer(Long manufacturerId, ManufacturerRequestDTO manufacturerRequestDTO) {
        Manufacturer manufacturer = manufacturerValidator.getManufacturerByIdValidation(manufacturerId);
        manufacturerValidator.addManufacturerValidation(manufacturerRequestDTO);

        manufacturer.setName(manufacturerRequestDTO.getName());
        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);

        return manufacturerMapper.toDTO(savedManufacturer);
    }

    /**
     * @param id
     * @return DTO of deleted manufacture
     */
    public ManufacturerResponseDTO deleteManufacturer(Long id) {
        Manufacturer manufacturer = manufacturerValidator.getManufacturerByIdValidation(id);
        manufacturerRepository.delete(manufacturer);
        return manufacturerMapper.toDTO(manufacturer);
    }

}
