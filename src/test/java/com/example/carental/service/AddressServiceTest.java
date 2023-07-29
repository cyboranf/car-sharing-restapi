package com.example.carental.service;

import com.example.carental.dto.address.AddressRequestDTO;
import com.example.carental.dto.address.AddressResponseDTO;
import com.example.carental.mapper.AddressMapper;
import com.example.carental.model.Address;
import com.example.carental.repository.AddressRepository;
import com.example.carental.validation.AddressValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private AddressValidator addressValidator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAddress() {
        AddressRequestDTO request = new AddressRequestDTO();
        request.setStreet("Street");
        request.setCity("City");
        request.setState("State");
        request.setCountry("Country");

        Address address = new Address();
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCountry(request.getCountry());

        AddressResponseDTO responseDTO = new AddressResponseDTO();
        responseDTO.setStreet(request.getStreet());
        responseDTO.setCity(request.getCity());
        responseDTO.setState(request.getState());
        responseDTO.setCountry(request.getCountry());

        when(addressMapper.fromDTO(any(AddressRequestDTO.class))).thenReturn(address);
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        when(addressMapper.toDTO(any(Address.class))).thenReturn(responseDTO);

        AddressResponseDTO response = addressService.createAddress(request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testGetAddress() {
        Long addressId = 1L;
        Address address = new Address();
        when(addressValidator.getByIdValidation(addressId)).thenReturn(address);

        AddressResponseDTO responseDTO = new AddressResponseDTO();
        when(addressMapper.toDTO(address)).thenReturn(responseDTO);

        AddressResponseDTO response = addressService.getAddress(addressId);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testGetAllAddresses() {
        Address address1 = new Address();
        Address address2 = new Address();
        when(addressRepository.findAll()).thenReturn(Arrays.asList(address1, address2));

        AddressResponseDTO responseDTO1 = new AddressResponseDTO();
        AddressResponseDTO responseDTO2 = new AddressResponseDTO();
        when(addressMapper.toDTO(address1)).thenReturn(responseDTO1);
        when(addressMapper.toDTO(address2)).thenReturn(responseDTO2);

        List<AddressResponseDTO> response = addressService.getAllAddresses();

        assertEquals(2, response.size());
    }

    @Test
    public void testUpdateAddress() {
        Long addressId = 1L;

        AddressRequestDTO request = new AddressRequestDTO();
        request.setStreet("New Street");
        request.setCity("New City");
        request.setState("New State");
        request.setCountry("New Country");

        Address address = new Address();
        address.setId(addressId);
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCountry(request.getCountry());

        AddressResponseDTO responseDTO = new AddressResponseDTO();
        responseDTO.setStreet(request.getStreet());
        responseDTO.setCity(request.getCity());
        responseDTO.setState(request.getState());
        responseDTO.setCountry(request.getCountry());

        when(addressValidator.getByIdValidation(anyLong())).thenReturn(address);
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        when(addressMapper.toDTO(any(Address.class))).thenReturn(responseDTO);

        AddressResponseDTO response = addressService.updateAddress(addressId, request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testDeleteAddress() {
        Long addressId = 1L;
        Address address = new Address();
        address.setId(addressId);

        AddressResponseDTO responseDTO = new AddressResponseDTO();
        when(addressValidator.getByIdValidation(addressId)).thenReturn(address);
        when(addressMapper.toDTO(address)).thenReturn(responseDTO);

        AddressResponseDTO response = addressService.deleteAddress(addressId);

        assertEquals(responseDTO, response);
        verify(addressRepository, times(1)).deleteById(addressId);
    }
}
