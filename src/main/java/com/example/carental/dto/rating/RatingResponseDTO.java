package com.example.carental.dto.rating;

import com.example.carental.model.enums.RatingValue;
import lombok.Data;

@Data
public class RatingResponseDTO {
    private Long id;
    private RatingValue rating;
    private String comment;
    private Long raterId;
    private Long carId;
}
