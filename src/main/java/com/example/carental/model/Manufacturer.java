package com.example.carental.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manufacturer")
@Data
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private List<Car> cars;
}
