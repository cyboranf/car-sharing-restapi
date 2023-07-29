package com.example.carental.service;

import com.example.carental.dto.manufacturer.ManufacturerRequestDTO;
import com.example.carental.dto.manufacturer.ManufacturerResponseDTO;
import com.example.carental.mapper.ManufacturerMapper;
import com.example.carental.model.Manufacturer;
import com.example.carental.repository.ManufacturerRepository;
import com.example.carental.validation.ManufacturerValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ManufacturerServiceTest {

    @InjectMocks
    private ManufacturerService manufacturerService;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @Mock
    private ManufacturerMapper manufacturerMapper;

    @Mock
    private ManufacturerValidator manufacturerValidator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddManufacturer() {
        ManufacturerRequestDTO request = new ManufacturerRequestDTO();
        request.setName("Manufacturer1");

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Manufacturer1");

        ManufacturerResponseDTO responseDTO = new ManufacturerResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("Manufacturer1");

        when(manufacturerMapper.fromDTO(any(ManufacturerRequestDTO.class))).thenReturn(manufacturer);
        when(manufacturerRepository.save(any(Manufacturer.class))).thenReturn(manufacturer);
        when(manufacturerMapper.toDTO(any(Manufacturer.class))).thenReturn(responseDTO);

        ManufacturerResponseDTO response = manufacturerService.addManufacturer(request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testGetAllManufacturers() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Manufacturer1");

        ManufacturerResponseDTO responseDTO = new ManufacturerResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("Manufacturer1");

        when(manufacturerRepository.findAll()).thenReturn(Arrays.asList(manufacturer));
        when(manufacturerMapper.toDTO(any(Manufacturer.class))).thenReturn(responseDTO);

        List<ManufacturerResponseDTO> response = manufacturerService.getAllManufacturers();

        assertEquals(1, response.size());
        assertEquals(responseDTO, response.get(0));
    }

    @Test
    public void testGetManufacturerById() {
        Long manufacturerId = 1L;

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(manufacturerId);
        manufacturer.setName("Manufacturer1");

        ManufacturerResponseDTO responseDTO = new ManufacturerResponseDTO();
        responseDTO.setId(manufacturerId);
        responseDTO.setName("Manufacturer1");

        when(manufacturerValidator.getManufacturerByIdValidation(anyLong())).thenReturn(manufacturer);
        when(manufacturerMapper.toDTO(any(Manufacturer.class))).thenReturn(responseDTO);

        ManufacturerResponseDTO response = manufacturerService.getManufacturerById(manufacturerId);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testUpdateManufacturer() {
        Long manufacturerId = 1L;

        ManufacturerRequestDTO request = new ManufacturerRequestDTO();
        request.setName("Manufacturer1");

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(manufacturerId);
        manufacturer.setName("Manufacturer1");

        ManufacturerResponseDTO responseDTO = new ManufacturerResponseDTO();
        responseDTO.setId(manufacturerId);
        responseDTO.setName("Manufacturer1");

        when(manufacturerValidator.getManufacturerByIdValidation(anyLong())).thenReturn(manufacturer);
        when(manufacturerRepository.save(any(Manufacturer.class))).thenReturn(manufacturer);
        when(manufacturerMapper.toDTO(any(Manufacturer.class))).thenReturn(responseDTO);

        ManufacturerResponseDTO response = manufacturerService.updateManufacturer(manufacturerId, request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testDeleteManufacturer() {
        Long manufacturerId = 1L;

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(manufacturerId);
        manufacturer.setName("Manufacturer1");

        ManufacturerResponseDTO responseDTO = new ManufacturerResponseDTO();
        responseDTO.setId(manufacturerId);
        responseDTO.setName("Manufacturer1");

        when(manufacturerValidator.getManufacturerByIdValidation(anyLong())).thenReturn(manufacturer);
        when(manufacturerMapper.toDTO(any(Manufacturer.class))).thenReturn(responseDTO);

        ManufacturerResponseDTO response = manufacturerService.deleteManufacturer(manufacturerId);

        assertEquals(responseDTO, response);

        verify(manufacturerRepository, times(1)).delete(manufacturer);
    }
}
