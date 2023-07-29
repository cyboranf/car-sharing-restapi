package com.example.carental.service;

import com.example.carental.dto.rent.RentRequestDTO;
import com.example.carental.dto.rent.RentResponseDTO;
import com.example.carental.mapper.RentMapper;
import com.example.carental.model.Rent;
import com.example.carental.repository.RentRepository;
import com.example.carental.validation.RentValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RentServiceTest {

    @InjectMocks
    private RentService rentService;

    @Mock
    private RentRepository rentRepository;

    @Mock
    private RentMapper rentMapper;

    @Mock
    private RentValidator rentValidator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRentCar() {
        RentRequestDTO request = new RentRequestDTO();
        request.setStartDate(LocalDateTime.now());
        request.setEndDate(LocalDateTime.now().plusDays(1));
        request.setCarId(1L);

        Rent rent = new Rent();
        rent.setStartDate(request.getStartDate());
        rent.setEndDate(request.getEndDate());

        RentResponseDTO responseDTO = new RentResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setStartDate(request.getStartDate());
        responseDTO.setEndDate(request.getEndDate());
        responseDTO.setCarId(request.getCarId());

        when(rentMapper.fromDTO(any(RentRequestDTO.class))).thenReturn(rent);
        when(rentRepository.save(any(Rent.class))).thenReturn(rent);
        when(rentMapper.toDTO(any(Rent.class))).thenReturn(responseDTO);

        RentResponseDTO response = rentService.rentCar(request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testGetRentsByUserId() {
        Rent rent = new Rent();
        rent.setStartDate(LocalDateTime.now());
        rent.setEndDate(LocalDateTime.now().plusDays(1));

        RentResponseDTO responseDTO = new RentResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setStartDate(rent.getStartDate());
        responseDTO.setEndDate(rent.getEndDate());
        responseDTO.setCarId(1L);

        when(rentRepository.findByUserId(anyLong())).thenReturn(Arrays.asList(rent));
        when(rentMapper.toDTO(any(Rent.class))).thenReturn(responseDTO);

        List<RentResponseDTO> response = rentService.getRentsByUserId(1L);

        assertEquals(1, response.size());
        assertEquals(responseDTO, response.get(0));
    }

    @Test
    public void testGetRentById() {
        Rent rent = new Rent();
        rent.setStartDate(LocalDateTime.now());
        rent.setEndDate(LocalDateTime.now().plusDays(1));

        RentResponseDTO responseDTO = new RentResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setStartDate(rent.getStartDate());
        responseDTO.setEndDate(rent.getEndDate());
        responseDTO.setCarId(1L);

        when(rentValidator.getRentByIdValidation(anyLong())).thenReturn(rent);
        when(rentMapper.toDTO(any(Rent.class))).thenReturn(responseDTO);

        RentResponseDTO response = rentService.getRentById(1L);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testUpdateRent() {
        RentRequestDTO request = new RentRequestDTO();
        request.setStartDate(LocalDateTime.now());
        request.setEndDate(LocalDateTime.now().plusDays(1));
        request.setCarId(1L);

        Rent rent = new Rent();
        rent.setStartDate(request.getStartDate());
        rent.setEndDate(request.getEndDate());

        RentResponseDTO responseDTO = new RentResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setStartDate(request.getStartDate());
        responseDTO.setEndDate(request.getEndDate());
        responseDTO.setCarId(request.getCarId());

        when(rentMapper.fromDTO(any(RentRequestDTO.class))).thenReturn(rent);
        when(rentRepository.save(any(Rent.class))).thenReturn(rent);
        when(rentMapper.toDTO(any(Rent.class))).thenReturn(responseDTO);

        RentResponseDTO response = rentService.updateRent(1L, request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testDeleteRentById() {
        Rent rent = new Rent();
        rent.setStartDate(LocalDateTime.now());
        rent.setEndDate(LocalDateTime.now().plusDays(1));

        RentResponseDTO responseDTO = new RentResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setStartDate(rent.getStartDate());
        responseDTO.setEndDate(rent.getEndDate());
        responseDTO.setCarId(1L);

        when(rentValidator.getRentByIdValidation(anyLong())).thenReturn(rent);
        when(rentMapper.toDTO(any(Rent.class))).thenReturn(responseDTO);

        RentResponseDTO response = rentService.deleteRentById(1L);

        assertEquals(responseDTO, response);

        verify(rentRepository, times(1)).delete(rent);
    }
}
