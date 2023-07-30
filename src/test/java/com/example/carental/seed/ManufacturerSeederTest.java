package com.example.carental.seed;

import com.example.carental.model.Manufacturer;
import com.example.carental.repository.ManufacturerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ManufacturerSeederTest {

    @InjectMocks
    private ManufacturerSeeder manufacturerSeeder;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRunWhenManufacturerRepositoryIsEmpty() throws Exception {
        when(manufacturerRepository.count()).thenReturn(0L);

        manufacturerSeeder.run();

        verify(manufacturerRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void testRunWhenManufacturerRepositoryIsNotEmpty() throws Exception {
        when(manufacturerRepository.count()).thenReturn(4L);

        manufacturerSeeder.run();

        verify(manufacturerRepository, never()).saveAll(anyList());
    }
}
