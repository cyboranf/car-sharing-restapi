package com.example.carental.seed;

import com.example.carental.model.Car;
import com.example.carental.model.Image;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class ImageSeederTest {

    @InjectMocks
    private ImageSeeder imageSeeder;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRunWhenImageRepositoryIsEmpty() throws Exception {
        when(imageRepository.count()).thenReturn(0L);
        when(carRepository.findById(anyLong())).thenReturn(Optional.of(new Car()));

        imageSeeder.run();

        verify(imageRepository, times(4)).save(any(Image.class));
    }

    @Test
    public void testRunWhenImageRepositoryIsNotEmpty() throws Exception {
        when(imageRepository.count()).thenReturn(4L);

        imageSeeder.run();

        verify(imageRepository, never()).save(any(Image.class));
    }
}
