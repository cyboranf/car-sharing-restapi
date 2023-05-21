package com.example.carental.model;

import com.example.carental.model.enums.RatingValue;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "rating")
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RatingValue rating;

    @Column(length = 500)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User rater;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
