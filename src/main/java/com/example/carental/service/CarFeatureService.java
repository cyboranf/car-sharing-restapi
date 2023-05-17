package com.example.carental.service;

import com.example.carental.repository.CarFeatureRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CarFeatureService {
    private final CarFeatureRepository carFeatureRepository;

    public CarFeatureService(CarFeatureRepository carFeatureRepository) {
        this.carFeatureRepository = carFeatureRepository;
    }
}
