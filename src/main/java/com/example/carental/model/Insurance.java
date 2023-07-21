package com.example.carental.model;

import com.example.carental.model.enums.InsuranceProvider;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "insurance")
@Data
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String policyNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InsuranceProvider provider;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
