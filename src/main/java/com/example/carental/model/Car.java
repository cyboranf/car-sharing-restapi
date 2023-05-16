package com.example.carental.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "car")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int seats;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "car_status_id")
    private CarStatus status;

    @ManyToOne
    @JoinColumn(name = "car_type_id")
    private CarType type;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "car")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "car")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "car")
    private List<Rent> rents;

    @ManyToMany
    @JoinTable(
            name = "car_car_feature",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    private Set<CarFeature> features;
}
