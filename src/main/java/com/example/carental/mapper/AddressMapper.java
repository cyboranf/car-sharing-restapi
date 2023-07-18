package com.example.carental.mapper;

import com.example.carental.model.Address;
import com.example.carental.dto.address.AddressRequestDTO;
import com.example.carental.dto.address.AddressResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressResponseDTO toDTO(Address address) {
        AddressResponseDTO dto = new AddressResponseDTO();
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setCountry(address.getCountry());
        return dto;
    }

    public Address fromDTO(AddressRequestDTO addressRequestDTO) {
        Address address = new Address();
        address.setStreet(addressRequestDTO.getStreet());
        address.setCity(addressRequestDTO.getCity());
        address.setState(addressRequestDTO.getState());
        address.setCountry(addressRequestDTO.getCountry());
        return address;
    }
}
