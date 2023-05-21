package com.example.carental.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class CarFeatureId implements Serializable {
    private Long carId;
    private Long featureId;
}
