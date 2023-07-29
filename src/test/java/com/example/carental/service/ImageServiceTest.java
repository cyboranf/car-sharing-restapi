package com.example.carental.service;

import com.example.carental.dto.image.ImageRequestDTO;
import com.example.carental.dto.image.ImageResponseDTO;
import com.example.carental.mapper.ImageMapper;
import com.example.carental.model.Car;
import com.example.carental.model.Image;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.ImageRepository;
import com.example.carental.validation.ImageValidator;
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

public class ImageServiceTest {

    @InjectMocks
    private ImageService imageService;

    @Mock
    private ImageRepository imageRepository;

    @Mock
    private ImageMapper imageMapper;
    @Mock
    private CarRepository carRepository;

    @Mock
    private ImageValidator imageValidator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUploadImage() {
        ImageRequestDTO request = new ImageRequestDTO();
        request.setUrl("url1");
        request.setCarId(1L);

        Image image = new Image();
        image.setUrl("url1");

        ImageResponseDTO responseDTO = new ImageResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setUrl("url1");

        when(carRepository.getById(anyLong())).thenReturn(new Car());
        when(imageMapper.fromDTO(any(ImageRequestDTO.class))).thenReturn(image);
        when(imageRepository.save(any(Image.class))).thenReturn(image);
        when(imageMapper.toDTO(any(Image.class))).thenReturn(responseDTO);

        ImageResponseDTO response = imageService.uploadImage(request);

        assertEquals(responseDTO, response);
    }


    @Test
    public void testGetAllImages() {
        Image image = new Image();
        image.setUrl("url1");

        ImageResponseDTO responseDTO = new ImageResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setUrl("url1");

        when(imageRepository.findAll()).thenReturn(Arrays.asList(image));
        when(imageMapper.toDTO(any(Image.class))).thenReturn(responseDTO);

        List<ImageResponseDTO> response = imageService.getAllImages();

        assertEquals(1, response.size());
        assertEquals(responseDTO, response.get(0));
    }

    @Test
    public void testGetImageById() {
        Long imageId = 1L;

        Image image = new Image();
        image.setId(imageId);
        image.setUrl("url1");

        ImageResponseDTO responseDTO = new ImageResponseDTO();
        responseDTO.setId(imageId);
        responseDTO.setUrl("url1");

        when(imageValidator.getByIdImageValidation(anyLong())).thenReturn(image);
        when(imageMapper.toDTO(any(Image.class))).thenReturn(responseDTO);

        ImageResponseDTO response = imageService.getImageById(imageId);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testUpdateImage() {
        Long imageId = 1L;

        ImageRequestDTO request = new ImageRequestDTO();
        request.setUrl("url1");
        request.setCarId(1L);

        Image image = new Image();
        image.setId(imageId);
        image.setUrl("url1");

        ImageResponseDTO responseDTO = new ImageResponseDTO();
        responseDTO.setId(imageId);
        responseDTO.setUrl("url1");

        when(imageValidator.getByIdImageValidation(anyLong())).thenReturn(image);
        when(carRepository.getById(anyLong())).thenReturn(new Car());
        when(imageRepository.save(any(Image.class))).thenReturn(image);
        when(imageMapper.toDTO(any(Image.class))).thenReturn(responseDTO);

        ImageResponseDTO response = imageService.updateImage(imageId, request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testDeleteImage() {
        Long imageId = 1L;

        Image image = new Image();
        image.setId(imageId);
        image.setUrl("url1");

        ImageResponseDTO responseDTO = new ImageResponseDTO();
        responseDTO.setId(imageId);
        responseDTO.setUrl("url1");

        when(imageValidator.getByIdImageValidation(anyLong())).thenReturn(image);
        when(imageMapper.toDTO(any(Image.class))).thenReturn(responseDTO);

        ImageResponseDTO response = imageService.deleteImage(imageId);

        assertEquals(responseDTO, response);

        verify(imageRepository, times(1)).delete(image);
    }
}
