package com.example.carental.service;

import com.example.carental.dto.carStatus.CarStatusRequestDTO;
import com.example.carental.dto.carStatus.CarStatusResponseDTO;
import com.example.carental.mapper.CarStatusMapper;
import com.example.carental.model.CarStatus;
import com.example.carental.model.enums.CarStatusValue;
import com.example.carental.repository.CarStatusRepository;
import com.example.carental.validation.CarStatusValidator;
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

public class CarStatusServiceTest {

    @InjectMocks
    private CarStatusService carStatusService;

    @Mock
    private CarStatusRepository carStatusRepository;

    @Mock
    private CarStatusMapper carStatusMapper;

    @Mock
    private CarStatusValidator carStatusValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCarStatus() {
        CarStatusRequestDTO request = new CarStatusRequestDTO();
        request.setStatus(CarStatusValue.AVAILABLE);

        CarStatus carStatus = new CarStatus();
        carStatus.setStatus(CarStatusValue.AVAILABLE);

        CarStatusResponseDTO responseDTO = new CarStatusResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setStatus(CarStatusValue.AVAILABLE);

        when(carStatusMapper.fromDTO(any(CarStatusRequestDTO.class))).thenReturn(carStatus);
        when(carStatusRepository.save(any(CarStatus.class))).thenReturn(carStatus);
        when(carStatusMapper.toDTO(any(CarStatus.class))).thenReturn(responseDTO);

        CarStatusResponseDTO response = carStatusService.createCarStatus(request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testGetCarStatus() {
        Long carStatusId = 1L;

        CarStatus carStatus = new CarStatus();
        carStatus.setId(carStatusId);
        carStatus.setStatus(CarStatusValue.AVAILABLE);

        CarStatusResponseDTO responseDTO = new CarStatusResponseDTO();
        responseDTO.setId(carStatusId);
        responseDTO.setStatus(CarStatusValue.AVAILABLE);

        when(carStatusValidator.getByIdValidation(anyLong())).thenReturn(carStatus);
        when(carStatusMapper.toDTO(any(CarStatus.class))).thenReturn(responseDTO);

        CarStatusResponseDTO response = carStatusService.getCarStatus(carStatusId);

        assertEquals(responseDTO, response);
    }
    @Test
    public void testGetAllCarStatuses() {
        CarStatus carStatus1 = new CarStatus();
        carStatus1.setId(1L);
        carStatus1.setStatus(CarStatusValue.AVAILABLE);

        CarStatus carStatus2 = new CarStatus();
        carStatus2.setId(2L);
        carStatus2.setStatus(CarStatusValue.RENTED);

        CarStatusResponseDTO responseDTO1 = new CarStatusResponseDTO();
        responseDTO1.setId(1L);
        responseDTO1.setStatus(CarStatusValue.AVAILABLE);

        CarStatusResponseDTO responseDTO2 = new CarStatusResponseDTO();
        responseDTO2.setId(2L);
        responseDTO2.setStatus(CarStatusValue.RENTED);

        when(carStatusRepository.findAll()).thenReturn(Arrays.asList(carStatus1, carStatus2));
        when(carStatusMapper.toDTO(carStatus1)).thenReturn(responseDTO1);
        when(carStatusMapper.toDTO(carStatus2)).thenReturn(responseDTO2);

        List<CarStatusResponseDTO> response = carStatusService.getAllCarStatuses();

        assertEquals(2, response.size());
        assertEquals(responseDTO1, response.get(0));
        assertEquals(responseDTO2, response.get(1));
    }

    @Test
    public void testUpdateCarStatus() {
        Long carStatusId = 1L;

        CarStatusRequestDTO request = new CarStatusRequestDTO();
        request.setStatus(CarStatusValue.RENTED);

        CarStatus carStatus = new CarStatus();
        carStatus.setId(carStatusId);
        carStatus.setStatus(CarStatusValue.RENTED);

        CarStatusResponseDTO responseDTO = new CarStatusResponseDTO();
        responseDTO.setId(carStatusId);
        responseDTO.setStatus(CarStatusValue.RENTED);

        when(carStatusValidator.getByIdValidation(anyLong())).thenReturn(carStatus);
        when(carStatusRepository.save(any(CarStatus.class))).thenReturn(carStatus);
        when(carStatusMapper.toDTO(any(CarStatus.class))).thenReturn(responseDTO);

        CarStatusResponseDTO response = carStatusService.updateCarStatus(carStatusId, request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testDeleteCarStatus() {
        Long carStatusId = 1L;

        CarStatus carStatus = new CarStatus();
        carStatus.setId(carStatusId);
        carStatus.setStatus(CarStatusValue.AVAILABLE);

        CarStatusResponseDTO responseDTO = new CarStatusResponseDTO();
        responseDTO.setId(carStatusId);
        responseDTO.setStatus(CarStatusValue.AVAILABLE);

        when(carStatusValidator.getByIdValidation(anyLong())).thenReturn(carStatus);
        when(carStatusMapper.toDTO(any(CarStatus.class))).thenReturn(responseDTO);

        CarStatusResponseDTO response = carStatusService.deleteCarStatus(carStatusId);

        assertEquals(responseDTO, response);
    }

}
