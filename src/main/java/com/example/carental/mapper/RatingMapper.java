package com.example.carental.mapper;

import com.example.carental.model.Rating;
import com.example.carental.dto.rating.RatingRequestDTO;
import com.example.carental.dto.rating.RatingResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

    public RatingResponseDTO toDTO(Rating rating) {
        RatingResponseDTO dto = new RatingResponseDTO();
        dto.setId(rating.getId());
        dto.setRating(rating.getRating());
        dto.setComment(rating.getComment());
        dto.setRaterId(rating.getRater().getId());
        dto.setCarId(rating.getCar().getId());
        return dto;
    }

    public Rating fromDTO(RatingRequestDTO ratingRequestDTO) {
        Rating rating = new Rating();
        rating.setRating(ratingRequestDTO.getRating());
        rating.setComment(ratingRequestDTO.getComment());
        return rating;
    }
}
