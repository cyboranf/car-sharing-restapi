package com.example.carental.service;

import com.example.carental.dto.rent.RentRequestDTO;
import com.example.carental.dto.rent.RentResponseDTO;
import com.example.carental.mapper.RentMapper;
import com.example.carental.model.Rent;
import com.example.carental.repository.RentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RentService {
    private final RentRepository rentRepository;
    private final RentMapper rentMapper;

    public RentService(RentRepository rentRepository, RentMapper rentMapper) {
        this.rentRepository = rentRepository;
        this.rentMapper = rentMapper;
    }

    public RentResponseDTO createRent(RentRequestDTO rentRequestDTO) {
        Rent rent = rentMapper.toEntity(rentRequestDTO);
        Rent savedRent = rentRepository.save(rent);
        return rentMapper.toDTO(savedRent);
    }

    public List<RentResponseDTO> getRentsByUserId(Long userId) {
        List<Rent> rents = rentRepository.findByUserId(userId);
        return rents.stream()
                .map(rentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RentResponseDTO getRentById(Long rentId) {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new IllegalArgumentException("Rent not found"));
        return rentMapper.toDTO(rent);
    }

    public Rent getRentById2(Long rentId) {
        return rentRepository.findById(rentId)
                .orElseThrow(() -> new IllegalArgumentException("Rent not found"));
    }
    public RentResponseDTO updateRent(Long rentId, RentRequestDTO rentRequestDTO) {
        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new IllegalArgumentException("Rent not found"));
        rent.setStartDate(rentRequestDTO.getStartDate());
        rent.setEndDate(rentRequestDTO.getEndDate());
        Rent updatedRent = rentRepository.save(rent);
        return rentMapper.toDTO(updatedRent);
    }

    public void deleteRentById(Long rentId) {
        if (!rentRepository.existsById(rentId)) {
            throw new IllegalArgumentException("Rent not found");
        }
        rentRepository.deleteById(rentId);
    }
}
