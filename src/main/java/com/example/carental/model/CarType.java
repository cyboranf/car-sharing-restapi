package com.example.carental.model;

import com.example.carental.model.enums.CarTypeValue;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "car_type")
@Data
public class CarType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarTypeValue type;

    @OneToMany(mappedBy = "type")
    private List<Car> cars;
}
