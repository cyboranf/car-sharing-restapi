package com.example.carental.service;

import com.example.carental.repository.CarFeatureRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CarFeatureService {
    private final CarFeatureRepository carFeatureRepository;

    public CarFeatureService(CarFeatureRepository carFeatureRepository) {
        this.carFeatureRepository = carFeatureRepository;
    }
}
