package com.example.carental.repository;

import com.example.carental.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTypeRepository extends JpaRepository<CarType, Long> {
}
