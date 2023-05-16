package com.example.carental.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class CarFeatureId implements Serializable {
    private Long carId;
    private Long featureId;
}
