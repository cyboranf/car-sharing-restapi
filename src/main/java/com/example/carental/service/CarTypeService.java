package com.example.carental.service;

import com.example.carental.dto.carType.CarTypeRequestDTO;
import com.example.carental.dto.carType.CarTypeResponseDTO;
import com.example.carental.mapper.CarTypeMapper;
import com.example.carental.model.CarType;
import com.example.carental.repository.CarTypeRepository;
import com.example.carental.validation.CarTypeValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarTypeService {
    private final CarTypeRepository carTypeRepository;
    private final CarTypeMapper carTypeMapper;
    private final CarTypeValidator carTypeValidator;

    public CarTypeService(CarTypeRepository carTypeRepository, CarTypeMapper carTypeMapper, CarTypeValidator carTypeValidator) {
        this.carTypeRepository = carTypeRepository;
        this.carTypeMapper = carTypeMapper;
        this.carTypeValidator = carTypeValidator;
    }

    /**
     * @param carTypeRequestDTO
     * @return DTO of new CarType in offer
     */
    public CarTypeResponseDTO createCarType(CarTypeRequestDTO carTypeRequestDTO) {
        carTypeValidator.validateCarTypeDoesNotExist(carTypeRequestDTO);

        CarType carType = carTypeMapper.fromDTO(carTypeRequestDTO);
        CarType savedCarType = carTypeRepository.save(carType);

        return carTypeMapper.toDTO(savedCarType);
    }

    /**
     * @return DTO of all Car Type's
     */
    public List<CarTypeResponseDTO> getAllCarTypes() {
        return carTypeRepository.findAll().stream()
                .map(carTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param carTypeId
     * @return DTO of Car Type with id = carTypeId
     */
    public CarTypeResponseDTO getCarTypeById(Long carTypeId) {
        CarType carType = carTypeValidator.getCarTypeByIdValidation(carTypeId);
        return carTypeMapper.toDTO(carType);
    }

    /**
     * @param id
     * @param carTypeRequestDTO
     * @return DTO of updated Car Type
     */
    public CarTypeResponseDTO updateCarType(Long id, CarTypeRequestDTO carTypeRequestDTO) {
        CarType carType = carTypeValidator.getCarTypeByIdValidation(id);
        carTypeValidator.validateCarTypeDoesNotExist(carTypeRequestDTO);

        carType.setType(carTypeRequestDTO.getType());

        CarType savedCarType = carTypeRepository.save(carType);
        return carTypeMapper.toDTO(savedCarType);
    }

    /**
     * @param carTypeId
     * @return DTO of deleted Car Type with id = carTypeId
     */
    public CarTypeResponseDTO deleteCarType(Long carTypeId) {
        CarType carType = carTypeValidator.getCarTypeByIdValidation(carTypeId);
        carTypeRepository.delete(carType);
        return carTypeMapper.toDTO(carType);
    }


}
