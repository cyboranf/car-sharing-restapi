package com.example.carental.service;

import com.example.carental.dto.rent.RentRequestDTO;
import com.example.carental.dto.rent.RentResponseDTO;
import com.example.carental.mapper.RentMapper;
import com.example.carental.model.Rent;
import com.example.carental.repository.RentRepository;
import com.example.carental.validation.RentValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RentService {
    private final RentRepository rentRepository;
    private final RentMapper rentMapper;
    private final RentValidator rentValidator;

    public RentService(RentRepository rentRepository, RentMapper rentMapper, RentValidator rentValidator) {
        this.rentRepository = rentRepository;
        this.rentMapper = rentMapper;
        this.rentValidator = rentValidator;
    }

    /**
     * @param rentRequestDTO
     * @return DTO of new rent
     */
    public RentResponseDTO rentCar(RentRequestDTO rentRequestDTO) {
        rentValidator.rentCarValidation(rentRequestDTO);

        Rent rent = rentMapper.fromDTO(rentRequestDTO);
        Rent savedRent = rentRepository.save(rent);

        return rentMapper.toDTO(savedRent);
    }

    /**
     * @param userId
     * @return List of dto rented car by {userId}
     */
    public List<RentResponseDTO> getRentsByUserId(Long userId) {
        List<Rent> rents = rentRepository.findByUserId(userId);

        return rents.stream()
                .map(rentMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param rentId
     * @return dto of rent with id = rentId
     */
    public RentResponseDTO getRentById(Long rentId) {
        Rent rent = rentValidator.getRentByIdValidation(rentId);

        return rentMapper.toDTO(rent);
    }

    /**
     * @param rentId
     * @param rentRequestDTO
     * @return dto of updated Rent
     */
    public RentResponseDTO updateRent(Long rentId, RentRequestDTO rentRequestDTO) {
        rentValidator.rentCarValidation(rentRequestDTO);
        rentValidator.getRentByIdValidation(rentId);

        Rent rent = rentMapper.fromDTO(rentRequestDTO);

        Rent updatedRent = rentRepository.save(rent);

        return rentMapper.toDTO(updatedRent);
    }

    /**
     * @param rentId
     * @return dto of deleted Rent
     */
    public RentResponseDTO deleteRentById(Long rentId) {
        Rent rent = rentValidator.getRentByIdValidation(rentId);

        return rentMapper.toDTO(rent);
    }
}
