package com.example.carental.service;

import com.example.carental.dto.rating.RatingRequestDTO;
import com.example.carental.dto.rating.RatingResponseDTO;
import com.example.carental.mapper.RatingMapper;
import com.example.carental.model.Car;
import com.example.carental.model.Rating;
import com.example.carental.model.enums.RatingValue;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.RatingRepository;
import com.example.carental.validation.RatingValidator;
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

public class RatingServiceTest {

    @InjectMocks
    private RatingService ratingService;

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private RatingMapper ratingMapper;

    @Mock
    private RatingValidator ratingValidator;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddRating() {
        Long carId = 1L;
        RatingRequestDTO request = new RatingRequestDTO();
        request.setRating(RatingValue.COMPLETED);
        request.setComment("Great car!");
        request.setRaterId(1L);
        request.setCarId(carId);

        Car car = new Car();
        car.setId(carId);

        Rating rating = new Rating();
        rating.setRating(request.getRating());
        rating.setComment(request.getComment());
        rating.setCar(car);

        RatingResponseDTO responseDTO = new RatingResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setRating(request.getRating());
        responseDTO.setComment(request.getComment());
        responseDTO.setRaterId(request.getRaterId());
        responseDTO.setCarId(carId);

        when(ratingValidator.addRatingValidation(anyLong(), any(RatingRequestDTO.class))).thenReturn(car);
        when(ratingMapper.fromDTO(any(RatingRequestDTO.class))).thenReturn(rating);
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);
        when(ratingMapper.toDTO(any(Rating.class))).thenReturn(responseDTO);

        RatingResponseDTO response = ratingService.addRating(carId, request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testGetCarRatings() {
        Long carId = 1L;
        Rating rating = new Rating();
        rating.setCar(new Car());
        rating.getCar().setId(carId);

        RatingResponseDTO responseDTO = new RatingResponseDTO();
        responseDTO.setCarId(carId);

        when(ratingRepository.findByCarId(anyLong())).thenReturn(Arrays.asList(rating));
        when(ratingMapper.toDTO(any(Rating.class))).thenReturn(responseDTO);

        List<RatingResponseDTO> response = ratingService.getCarRatings(carId);

        assertEquals(1, response.size());
        assertEquals(responseDTO, response.get(0));
    }
}