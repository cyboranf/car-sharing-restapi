package com.example.carental.service;

import com.example.carental.dto.rating.RatingRequestDTO;
import com.example.carental.dto.rating.RatingResponseDTO;
import com.example.carental.mapper.RatingMapper;
import com.example.carental.model.Rating;
import com.example.carental.repository.RatingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    private final CarService carService;
    public RatingService(RatingRepository ratingRepository, RatingMapper ratingMapper, CarService carService) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
        this.carService = carService;
    }

    public RatingResponseDTO addRating(Long carId, RatingRequestDTO ratingRequestDTO) {
        Rating rating = ratingMapper.toEntity(ratingRequestDTO);
        rating.setCar(carService.getCarById2(carId));
        Rating savedRating = ratingRepository.save(rating);
        return ratingMapper.toDTO(savedRating);
    }

    public List<RatingResponseDTO> getCarRatings(Long carId) {
        List<Rating> ratings = ratingRepository.findByCarId(carId);
        return ratings.stream()
                .map(ratingMapper::toDTO)
                .collect(Collectors.toList());
    }
}
