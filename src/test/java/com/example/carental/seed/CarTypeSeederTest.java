package com.example.carental.seed;

import com.example.carental.model.CarType;
import com.example.carental.model.enums.CarTypeValue;
import com.example.carental.repository.CarTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class CarTypeSeederTest {

    @InjectMocks
    private CarTypeSeeder carTypeSeeder;

    @Mock
    private CarTypeRepository carTypeRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRunWhenCarTypeRepositoryIsEmpty() throws Exception {
        when(carTypeRepository.count()).thenReturn(0L);

        carTypeSeeder.run();

        verify(carTypeRepository, times(CarTypeValue.values().length)).save(any(CarType.class));
    }

    @Test
    public void testRunWhenCarTypeRepositoryIsNotEmpty() throws Exception {
        when(carTypeRepository.count()).thenReturn((long) CarTypeValue.values().length);

        carTypeSeeder.run();

        verify(carTypeRepository, never()).save(any(CarType.class));
    }
}
