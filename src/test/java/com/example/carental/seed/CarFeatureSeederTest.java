package com.example.carental.seed;

import com.example.carental.model.CarFeature;
import com.example.carental.repository.CarFeatureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarFeatureSeederTest {

    @InjectMocks
    private CarFeatureSeeder carFeatureSeeder;

    @Mock
    private CarFeatureRepository carFeatureRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRunWhenCarFeatureRepositoryIsEmpty() throws Exception {
        when(carFeatureRepository.count()).thenReturn(0L);

        carFeatureSeeder.run();

        verify(carFeatureRepository, times(1)).saveAll(any(List.class));
    }

    @Test
    public void testRunWhenCarFeatureRepositoryIsNotEmpty() throws Exception {
        when(carFeatureRepository.count()).thenReturn(4L);

        carFeatureSeeder.run();

        verify(carFeatureRepository, never()).saveAll(any(List.class));
    }

    @Test
    public void testNewCarFeature() {
        String name = "Air Conditioning";

        CarFeature carFeature = carFeatureSeeder.newCarFeature(name);

        assertEquals(name, carFeature.getName());
    }
}
