package com.example.carental.dto.car;

import lombok.Data;

import java.util.Set;

@Data
public class CarResponseDTO {
    private Long id;
    private String brand;
    private String model;
    private String description;
    private int seats;
    private Long ownerId;
    private Long statusId;
    private Long typeId;
    private Long manufacturerId;
    private Set<Long> featureIds;
}
