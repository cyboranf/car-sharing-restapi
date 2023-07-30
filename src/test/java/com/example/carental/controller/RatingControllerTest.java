package com.example.carental.controller;

import com.example.carental.dto.rating.RatingRequestDTO;
import com.example.carental.dto.rating.RatingResponseDTO;
import com.example.carental.service.RatingService;
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

public class RatingControllerTest {

    @InjectMocks
    private RatingController ratingController;

    @Mock
    private RatingService ratingService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddRating() {
        RatingRequestDTO request = new RatingRequestDTO();
        RatingResponseDTO response = new RatingResponseDTO();
        when(ratingService.addRating(1L, request)).thenReturn(response);

        ResponseEntity<RatingResponseDTO> result = ratingController.addRating(1L, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testGetCarRatings() {
        RatingResponseDTO response1 = new RatingResponseDTO();
        RatingResponseDTO response2 = new RatingResponseDTO();
        List<RatingResponseDTO> responses = Arrays.asList(response1, response2);
        when(ratingService.getCarRatings(1L)).thenReturn(responses);

        ResponseEntity<List<RatingResponseDTO>> result = ratingController.getCarRatings(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }
}
