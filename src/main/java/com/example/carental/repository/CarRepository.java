package com.example.carental.repository;

import com.example.carental.model.Car;
import com.example.carental.model.enums.CarStatusValue;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByStatus_Status(CarStatusValue status);
}
