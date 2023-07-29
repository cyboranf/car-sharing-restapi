package com.example.carental.service;

import com.example.carental.dto.carFeature.CarFeatureResponseDTO;
import com.example.carental.exception.carFeature.CarFeatureNotFoundException;
import com.example.carental.model.CarFeature;
import com.example.carental.repository.CarFeatureRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class CarFeatureService {
    private final CarFeatureRepository carFeatureRepository;

    public CarFeatureService(CarFeatureRepository carFeatureRepository) {
        this.carFeatureRepository = carFeatureRepository;
    }

    public CarFeature findById(Long id) {
        return carFeatureRepository.findById(id)
                .orElseThrow(() -> new CarFeatureNotFoundException("Can not found"));
    }
}
