package com.example.carental.dto.address;

import lombok.Data;

@Data
public class AddressRequestDTO {
    private String street;
    private String city;
    private String state;
    private String country;
}
