package com.example.carental.repository;


import com.example.carental.model.CarFeature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarFeatureRepository extends JpaRepository<CarFeature, Long> {
}
