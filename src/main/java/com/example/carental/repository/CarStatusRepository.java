package com.example.carental.repository;

import com.example.carental.model.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarStatusRepository extends JpaRepository<CarStatus, Long> {
}
