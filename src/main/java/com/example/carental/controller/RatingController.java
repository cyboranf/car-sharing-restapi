package com.example.carental.controller;

import com.example.carental.dto.rating.RatingRequestDTO;
import com.example.carental.dto.rating.RatingResponseDTO;
import com.example.carental.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/{carId}/ratings")
    public ResponseEntity<RatingResponseDTO> addRating(
            @PathVariable Long carId,
            @RequestBody RatingRequestDTO ratingRequestDTO) {
        return ResponseEntity.ok(ratingService.addRating(carId, ratingRequestDTO));
    }

    @GetMapping("/{carId}/ratings")
    public ResponseEntity<List<RatingResponseDTO>> getCarRatings(@PathVariable Long carId) {
        return ResponseEntity.ok(ratingService.getCarRatings(carId));
    }
}
