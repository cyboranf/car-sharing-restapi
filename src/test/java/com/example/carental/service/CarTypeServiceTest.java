package com.example.carental.service;

import com.example.carental.dto.carType.CarTypeRequestDTO;
import com.example.carental.dto.carType.CarTypeResponseDTO;
import com.example.carental.mapper.CarTypeMapper;
import com.example.carental.model.CarType;
import com.example.carental.model.enums.CarTypeValue;
import com.example.carental.repository.CarTypeRepository;
import com.example.carental.validation.CarTypeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CarTypeServiceTest {

    @InjectMocks
    private CarTypeService carTypeService;

    @Mock
    private CarTypeRepository carTypeRepository;

    @Mock
    private CarTypeMapper carTypeMapper;

    @Mock
    private CarTypeValidator carTypeValidator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCarType() {
        CarTypeRequestDTO request = new CarTypeRequestDTO();
        request.setType(CarTypeValue.SEDAN);

        CarType carType = new CarType();
        carType.setType(CarTypeValue.SEDAN);

        CarTypeResponseDTO responseDTO = new CarTypeResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setType(CarTypeValue.SEDAN);

        when(carTypeMapper.fromDTO(any(CarTypeRequestDTO.class))).thenReturn(carType);
        when(carTypeRepository.save(any(CarType.class))).thenReturn(carType);
        when(carTypeMapper.toDTO(any(CarType.class))).thenReturn(responseDTO);

        CarTypeResponseDTO response = carTypeService.createCarType(request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testGetAllCarTypes() {
        CarType carType1 = new CarType();
        carType1.setType(CarTypeValue.SEDAN);

        CarType carType2 = new CarType();
        carType2.setType(CarTypeValue.SUV);

        CarTypeResponseDTO responseDTO1 = new CarTypeResponseDTO();
        responseDTO1.setId(1L);
        responseDTO1.setType(CarTypeValue.SEDAN);

        CarTypeResponseDTO responseDTO2 = new CarTypeResponseDTO();
        responseDTO2.setId(2L);
        responseDTO2.setType(CarTypeValue.SUV);

        when(carTypeRepository.findAll()).thenReturn(Arrays.asList(carType1, carType2));
        when(carTypeMapper.toDTO(carType1)).thenReturn(responseDTO1);
        when(carTypeMapper.toDTO(carType2)).thenReturn(responseDTO2);

        List<CarTypeResponseDTO> response = carTypeService.getAllCarTypes();

        assertEquals(2, response.size());
        assertEquals(responseDTO1, response.get(0));
        assertEquals(responseDTO2, response.get(1));
    }
    @Test
    public void testGetCarTypeById() {
        Long carTypeId = 1L;

        CarType carType = new CarType();
        carType.setId(carTypeId);
        carType.setType(CarTypeValue.SEDAN);

        CarTypeResponseDTO responseDTO = new CarTypeResponseDTO();
        responseDTO.setId(carTypeId);
        responseDTO.setType(CarTypeValue.SEDAN);

        when(carTypeValidator.getCarTypeByIdValidation(anyLong())).thenReturn(carType);
        when(carTypeMapper.toDTO(any(CarType.class))).thenReturn(responseDTO);

        CarTypeResponseDTO response = carTypeService.getCarTypeById(carTypeId);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testUpdateCarType() {
        Long carTypeId = 1L;

        CarTypeRequestDTO request = new CarTypeRequestDTO();
        request.setType(CarTypeValue.SUV);

        CarType carType = new CarType();
        carType.setId(carTypeId);
        carType.setType(CarTypeValue.SUV);

        CarTypeResponseDTO responseDTO = new CarTypeResponseDTO();
        responseDTO.setId(carTypeId);
        responseDTO.setType(CarTypeValue.SUV);

        when(carTypeValidator.getCarTypeByIdValidation(anyLong())).thenReturn(carType);
        when(carTypeRepository.save(any(CarType.class))).thenReturn(carType);
        when(carTypeMapper.toDTO(any(CarType.class))).thenReturn(responseDTO);

        CarTypeResponseDTO response = carTypeService.updateCarType(carTypeId, request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testDeleteCarType() {
        Long carTypeId = 1L;

        CarType carType = new CarType();
        carType.setId(carTypeId);
        carType.setType(CarTypeValue.SEDAN);

        CarTypeResponseDTO responseDTO = new CarTypeResponseDTO();
        responseDTO.setId(carTypeId);
        responseDTO.setType(CarTypeValue.SEDAN);

        when(carTypeValidator.getCarTypeByIdValidation(anyLong())).thenReturn(carType);
        when(carTypeMapper.toDTO(any(CarType.class))).thenReturn(responseDTO);

        CarTypeResponseDTO response = carTypeService.deleteCarType(carTypeId);

        assertEquals(responseDTO, response);
    }

}
