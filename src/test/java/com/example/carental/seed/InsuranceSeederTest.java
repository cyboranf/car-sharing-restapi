package com.example.carental.seed;

import com.example.carental.model.Car;
import com.example.carental.model.Insurance;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.InsuranceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class InsuranceSeederTest {

    @InjectMocks
    private InsuranceSeeder insuranceSeeder;

    @Mock
    private InsuranceRepository insuranceRepository;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRunWhenInsuranceRepositoryIsNotEmpty() throws Exception {
        when(insuranceRepository.count()).thenReturn(4L);

        insuranceSeeder.run();

        verify(insuranceRepository, never()).save(any(Insurance.class));
    }
}
