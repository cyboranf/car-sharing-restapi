package com.example.carental.model.enums;

public enum CarTypeValue {
    SEDAN("Sedan"),
    SUV("SUV"),
    COUPE("Coupe"),
    TRUCK("Truck"),
    VAN("Van");

    private final String displayName;

    CarTypeValue(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
