package com.example.carental.seed;

import com.example.carental.model.CarStatus;
import com.example.carental.model.enums.CarStatusValue;
import com.example.carental.repository.CarStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class CarStatusSeederTest {

    @InjectMocks
    private CarStatusSeeder carStatusSeeder;

    @Mock
    private CarStatusRepository carStatusRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRunWhenCarStatusRepositoryIsEmpty() throws Exception {
        when(carStatusRepository.count()).thenReturn(0L);

        carStatusSeeder.run();

        verify(carStatusRepository, times(CarStatusValue.values().length)).save(any(CarStatus.class));
    }

    @Test
    public void testRunWhenCarStatusRepositoryIsNotEmpty() throws Exception {
        when(carStatusRepository.count()).thenReturn((long) CarStatusValue.values().length);

        carStatusSeeder.run();

        verify(carStatusRepository, never()).save(any(CarStatus.class));
    }
}
