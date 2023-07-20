package com.example.carental.service;

import com.example.carental.dto.rating.RatingRequestDTO;
import com.example.carental.dto.rating.RatingResponseDTO;
import com.example.carental.mapper.RatingMapper;
import com.example.carental.model.Car;
import com.example.carental.model.Rating;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.RatingRepository;
import com.example.carental.validation.RatingValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final RatingValidator ratingValidator;
    private final CarRepository carRepository;

    public RatingService(RatingRepository ratingRepository, RatingMapper ratingMapper, RatingValidator ratingValidator, CarRepository carRepository) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
        this.ratingValidator = ratingValidator;
        this.carRepository = carRepository;
    }

    /**
     * @param carId
     * @param ratingRequestDTO
     * @return DTO of new rate of car with id = carId
     */
    public RatingResponseDTO addRating(Long carId, RatingRequestDTO ratingRequestDTO) {
        Car car = ratingValidator.addRatingValidation(carId, ratingRequestDTO);

        Rating rating = ratingMapper.fromDTO(ratingRequestDTO);

        rating.setCar(car);

        Rating savedRating = ratingRepository.save(rating);

        return ratingMapper.toDTO(savedRating);
    }

    /**
     * @param carId
     * @return DTO of all ratings of car with id = carId
     */
    public List<RatingResponseDTO> getCarRatings(Long carId) {
        List<Rating> ratings = ratingRepository.findByCarId(carId);
        return ratings.stream()
                .map(ratingMapper::toDTO)
                .collect(Collectors.toList());
    }
}
