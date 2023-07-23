package com.example.carental.service;

import com.example.carental.dto.address.AddressRequestDTO;
import com.example.carental.dto.address.AddressResponseDTO;
import com.example.carental.mapper.AddressMapper;
import com.example.carental.model.Address;
import com.example.carental.repository.AddressRepository;
import com.example.carental.validation.AddressValidator;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressValidator addressValidator;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressValidator addressValidator, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressValidator = addressValidator;
        this.addressMapper = addressMapper;
    }

    /**
     * @param addressRequestDTO
     * @return DTO of new Address
     */
    public AddressResponseDTO createAddress(AddressRequestDTO addressRequestDTO) {
        addressValidator.validate(addressRequestDTO);
        Address address = addressMapper.fromDTO(addressRequestDTO);
        Address savedAddress = addressRepository.save(address);
        return addressMapper.toDTO(savedAddress);
    }

    /**
     * @param addressId
     * @return DTO of Address with id = addressId
     */
    public AddressResponseDTO getAddress(Long addressId) {
        Address address = addressValidator.getByIdValidation(addressId);

        return addressMapper.toDTO(address);
    }

    /**
     * @return List of DTO of all Adresses
     */
    public List<AddressResponseDTO> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param id
     * @param addressRequestDTO
     * @return DTO of updated Address
     */
    public AddressResponseDTO updateAddress(Long id, AddressRequestDTO addressRequestDTO) {
        addressValidator.validate(addressRequestDTO);
        Address address = addressValidator.getByIdValidation(id);

        address.setStreet(addressRequestDTO.getStreet());
        address.setCity(addressRequestDTO.getCity());
        address.setState(addressRequestDTO.getState());
        address.setCountry(addressRequestDTO.getCountry());

        Address updatedAddress = addressRepository.save(address);
        return addressMapper.toDTO(updatedAddress);
    }

    /**
     * @param addressId
     * @return DTO of deleted Address with id = addressId
     */
    public AddressResponseDTO deleteAddress(Long addressId) {
        Address address = addressValidator.getByIdValidation(addressId);
        addressRepository.deleteById(addressId);
        return addressMapper.toDTO(address);
    }

}
