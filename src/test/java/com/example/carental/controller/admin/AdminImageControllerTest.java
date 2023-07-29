package com.example.carental.controller.admin;

import com.example.carental.controller.admin.AdminImageController;
import com.example.carental.dto.image.ImageResponseDTO;
import com.example.carental.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdminImageControllerTest {

    @InjectMocks
    private AdminImageController adminImageController;

    @Mock
    private ImageService imageService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllImages() {
        ImageResponseDTO response1 = new ImageResponseDTO();
        ImageResponseDTO response2 = new ImageResponseDTO();
        List<ImageResponseDTO> responses = Arrays.asList(response1, response2);
        when(imageService.getAllImages()).thenReturn(responses);

        ResponseEntity<List<ImageResponseDTO>> result = adminImageController.getAllImages();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }

    @Test
    public void testDeleteImage() {
        ImageResponseDTO response = new ImageResponseDTO();
        when(imageService.deleteImage(1L)).thenReturn(response);

        ResponseEntity<ImageResponseDTO> result = adminImageController.getImageById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
