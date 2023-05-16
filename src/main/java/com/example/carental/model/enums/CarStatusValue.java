package com.example.carental.model.enums;

public enum CarStatusValue {
    AVAILABLE("Car is available for renting"),
    RENTED("Car is currently rented"),
    UNDER_MAINTENANCE("Car is currently under maintenance");

    private final String description;

    CarStatusValue(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
