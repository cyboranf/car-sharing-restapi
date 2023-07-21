package com.example.carental.service;

import com.example.carental.dto.carStatus.CarStatusRequestDTO;
import com.example.carental.dto.carStatus.CarStatusResponseDTO;
import com.example.carental.mapper.CarStatusMapper;
import com.example.carental.model.CarStatus;
import com.example.carental.repository.CarStatusRepository;
import com.example.carental.validation.CarStatusValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarStatusService {
    private final CarStatusRepository carStatusRepository;
    private final CarStatusMapper carStatusMapper;
    private final CarStatusValidator carStatusValidator;

    public CarStatusService(CarStatusRepository carStatusRepository, CarStatusMapper carStatusMapper, CarStatusValidator carStatusValidator) {
        this.carStatusRepository = carStatusRepository;
        this.carStatusMapper = carStatusMapper;
        this.carStatusValidator = carStatusValidator;
    }

    /**
     * @param carStatusRequestDTO
     * @return DTO of new Car Status
     */
    public CarStatusResponseDTO createCarStatus(CarStatusRequestDTO carStatusRequestDTO) {
        carStatusValidator.createCarStatusValidation(carStatusRequestDTO);

        CarStatus carStatus = carStatusMapper.fromDTO(carStatusRequestDTO);
        CarStatus savedCarStatus = carStatusRepository.save(carStatus);

        return carStatusMapper.toDTO(savedCarStatus);
    }

    /**
     * @param carStatusId
     * @return DTO of CarStatus with id = carStatusId
     */
    public CarStatusResponseDTO getCarStatus(Long carStatusId) {
        CarStatus carStatus = carStatusValidator.getByIdValidation(carStatusId);

        return carStatusMapper.toDTO(carStatus);
    }

    /**
     * @return List of all DTO Car Statuses
     */
    public List<CarStatusResponseDTO> getAllCarStatuses() {
        List<CarStatus> carStatuses = carStatusRepository.findAll();
        return carStatuses.stream()
                .map(carStatusMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param carStatusId
     * @param carStatusRequestDTO
     * @return DTO of updated Car Status
     */
    public CarStatusResponseDTO updateCarStatus(Long carStatusId, CarStatusRequestDTO carStatusRequestDTO) {
        CarStatus carStatus = carStatusValidator.getByIdValidation(carStatusId);
        carStatusValidator.validateCarStatusValue(carStatusRequestDTO);

        carStatus.setStatus(carStatusRequestDTO.getStatus());
        CarStatus updatedCarStatus = carStatusRepository.save(carStatus);

        return carStatusMapper.toDTO(updatedCarStatus);
    }

    /**
     * @param carStatusId
     * @return DTO of deleted Car Status
     */
    public CarStatusResponseDTO deleteCarStatus(Long carStatusId) {
        CarStatus carStatusToDelete = carStatusValidator.getByIdValidation(carStatusId);
        carStatusRepository.delete(carStatusToDelete);
        return carStatusMapper.toDTO(carStatusToDelete);
    }

}
