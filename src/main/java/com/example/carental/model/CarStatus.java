package com.example.carental.model;

import com.example.carental.model.enums.CarStatusValue;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "car_status")
@Data
public class CarStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatusValue status;

    @OneToMany(mappedBy = "status")
    private List<Car> cars;
}
