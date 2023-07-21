package com.example.carental.repository;

import com.example.carental.model.CarType;
import com.example.carental.model.enums.CarTypeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarTypeRepository extends JpaRepository<CarType, Long> {
    Optional<CarType> findByType(CarTypeValue type);

}
