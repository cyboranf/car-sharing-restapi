package com.example.carental.service;

import com.example.carental.exception.carFeature.CarFeatureNotFoundException;
import com.example.carental.model.CarFeature;
import com.example.carental.repository.CarFeatureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CarFeatureServiceTest {

    @InjectMocks
    private CarFeatureService carFeatureService;

    @Mock
    private CarFeatureRepository carFeatureRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        CarFeature carFeature = new CarFeature();
        carFeature.setId(id);
        carFeature.setName("Feature1");

        when(carFeatureRepository.findById(id)).thenReturn(Optional.of(carFeature));

        CarFeature foundCarFeature = carFeatureService.findById(id);

        assertEquals(carFeature, foundCarFeature);
    }

    @Test
    public void testFindByIdNotFound() {
        Long id = 1L;

        when(carFeatureRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CarFeatureNotFoundException.class, () -> carFeatureService.findById(id));
    }
}
