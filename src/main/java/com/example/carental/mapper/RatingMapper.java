package com.example.carental.mapper;

import com.example.carental.dto.rating.RatingRequestDTO;
import com.example.carental.dto.rating.RatingResponseDTO;
import com.example.carental.model.Rating;
import com.example.carental.model.User;
import com.example.carental.service.CarService;
import com.example.carental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {
    private final UserService userService;
    private final CarService carService;

    @Autowired
    public RatingMapper(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }

    public Rating toEntity(RatingRequestDTO ratingRequestDTO) {
        Rating rating = new Rating();
        rating.setRating(ratingRequestDTO.getRating());
        rating.setComment(ratingRequestDTO.getComment());
        User rater = userService.getUser2(ratingRequestDTO.getRaterId());
        rating.setRater(rater);
        rating.setCar(carService.getCarById2(ratingRequestDTO.getCarId()));
        return rating;
    }

    public RatingResponseDTO toDTO(Rating rating) {
        RatingResponseDTO ratingResponseDTO = new RatingResponseDTO();
        ratingResponseDTO.setId(rating.getId());
        ratingResponseDTO.setRating(rating.getRating());
        ratingResponseDTO.setComment(rating.getComment());
        ratingResponseDTO.setRaterId(rating.getRater().getId());
        ratingResponseDTO.setCarId(rating.getCar().getId());
        return ratingResponseDTO;
    }
}
