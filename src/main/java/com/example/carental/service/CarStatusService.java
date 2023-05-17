package com.example.carental.service;

import com.example.carental.repository.CarStatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CarStatusService {
    private final CarStatusRepository carStatusRepository;

    public CarStatusService(CarStatusRepository carStatusRepository) {
        this.carStatusRepository = carStatusRepository;
    }
}
