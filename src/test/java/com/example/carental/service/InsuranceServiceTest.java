package com.example.carental.service;

import com.example.carental.dto.insurance.InsuranceRequestDTO;
import com.example.carental.dto.insurance.InsuranceResponseDTO;
import com.example.carental.mapper.InsuranceMapper;
import com.example.carental.model.Car;
import com.example.carental.model.Insurance;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.InsuranceRepository;
import com.example.carental.validation.InsuranceValidator;
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

public class InsuranceServiceTest {

    @InjectMocks
    private InsuranceService insuranceService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private InsuranceRepository insuranceRepository;

    @Mock
    private InsuranceMapper insuranceMapper;

    @Mock
    private InsuranceValidator insuranceValidator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetInsurance() {
        InsuranceRequestDTO request = new InsuranceRequestDTO();
        request.setPolicyNumber("Policy1");
        request.setCarId(1L);

        Insurance insurance = new Insurance();
        insurance.setPolicyNumber("Policy1");

        Car car = new Car();
        car.setId(1L);

        InsuranceResponseDTO responseDTO = new InsuranceResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setPolicyNumber("Policy1");
        responseDTO.setCarId(1L);

        when(insuranceMapper.fromDTO(any(InsuranceRequestDTO.class))).thenReturn(insurance);
        when(carRepository.getById(anyLong())).thenReturn(car);
        when(insuranceRepository.save(any(Insurance.class))).thenReturn(insurance);
        when(insuranceMapper.toDTO(any(Insurance.class))).thenReturn(responseDTO);

        InsuranceResponseDTO response = insuranceService.getInsurance(request);

        assertEquals(responseDTO, response);
    }


    @Test
    public void testGetAllInsurances() {
        Insurance insurance = new Insurance();
        insurance.setPolicyNumber("Policy1");

        InsuranceResponseDTO responseDTO = new InsuranceResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setPolicyNumber("Policy1");

        when(insuranceRepository.findAll()).thenReturn(Arrays.asList(insurance));
        when(insuranceMapper.toDTO(any(Insurance.class))).thenReturn(responseDTO);

        List<InsuranceResponseDTO> response = insuranceService.getAllInsurances();

        assertEquals(1, response.size());
        assertEquals(responseDTO, response.get(0));
    }

    @Test
    public void testGetInsuranceById() {
        Long insuranceId = 1L;

        Insurance insurance = new Insurance();
        insurance.setId(insuranceId);
        insurance.setPolicyNumber("Policy1");

        InsuranceResponseDTO responseDTO = new InsuranceResponseDTO();
        responseDTO.setId(insuranceId);
        responseDTO.setPolicyNumber("Policy1");

        when(insuranceValidator.getByIdValidation(anyLong())).thenReturn(insurance);
        when(insuranceMapper.toDTO(any(Insurance.class))).thenReturn(responseDTO);

        InsuranceResponseDTO response = insuranceService.getInsuranceById(insuranceId);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testUpdateInsurance() {
        Long insuranceId = 1L;

        InsuranceRequestDTO request = new InsuranceRequestDTO();
        request.setPolicyNumber("Policy1");
        request.setCarId(1L);

        Insurance insurance = new Insurance();
        insurance.setId(insuranceId);
        insurance.setPolicyNumber("Policy1");

        Car car = new Car();
        car.setId(1L);

        InsuranceResponseDTO responseDTO = new InsuranceResponseDTO();
        responseDTO.setId(insuranceId);
        responseDTO.setPolicyNumber("Policy1");
        responseDTO.setCarId(1L);

        when(insuranceValidator.updateInsuranceValidation(anyLong(), any(InsuranceRequestDTO.class))).thenReturn(insurance);
        when(carRepository.getById(anyLong())).thenReturn(car);
        when(insuranceRepository.save(any(Insurance.class))).thenReturn(insurance);
        when(insuranceMapper.toDTO(any(Insurance.class))).thenReturn(responseDTO);

        InsuranceResponseDTO response = insuranceService.updateInsurance(insuranceId, request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testDeleteInsurance() {
        Long insuranceId = 1L;

        Insurance insurance = new Insurance();
        insurance.setId(insuranceId);
        insurance.setPolicyNumber("Policy1");

        InsuranceResponseDTO responseDTO = new InsuranceResponseDTO();
        responseDTO.setId(insuranceId);
        responseDTO.setPolicyNumber("Policy1");

        when(insuranceValidator.getByIdValidation(anyLong())).thenReturn(insurance);
        when(insuranceMapper.toDTO(any(Insurance.class))).thenReturn(responseDTO);

        InsuranceResponseDTO response = insuranceService.deleteInsurance(insuranceId);

        assertEquals(responseDTO, response);

        verify(insuranceRepository, times(1)).delete(insurance);
    }
}
