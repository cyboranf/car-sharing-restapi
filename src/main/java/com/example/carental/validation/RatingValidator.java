package com.example.carental.validation;

import com.example.carental.dto.rating.RatingRequestDTO;
import com.example.carental.exception.car.CarNotFoundException;
import com.example.carental.exception.rating.InvalidRatingCommentException;
import com.example.carental.exception.rating.InvalidRatingException;
import com.example.carental.exception.user.UserNotFoundException;
import com.example.carental.model.Car;
import com.example.carental.model.enums.RatingValue;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.RatingRepository;
import com.example.carental.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RatingValidator {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public RatingValidator(RatingRepository ratingRepository, UserRepository userRepository, CarRepository carRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    /**
     * @param carId
     * @param ratingRequestDTO
     * @return Car that will have new rate
     */
    public Car addRatingValidation(Long carId, RatingRequestDTO ratingRequestDTO) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Can not found car with id = " + carId));
        if (ratingRequestDTO.getComment() == null || ratingRequestDTO.getComment().length() > 500) {
            throw new InvalidRatingCommentException("Comment of rating can not be null and can not have more than 500 characters length.");
        }
        userRepository.findById(ratingRequestDTO.getRaterId())
                .orElseThrow(() -> new UserNotFoundException("Can not found rater with id = " + ratingRequestDTO.getRaterId()));
        carRepository.findById(ratingRequestDTO.getCarId())
                .orElseThrow(() -> new CarNotFoundException("Can not add rate to car with id = " + ratingRequestDTO.getCarId()));
        if (ratingRequestDTO.getRating() == null || Arrays.stream(RatingValue.values()).noneMatch(val -> val.name().equals(ratingRequestDTO.getRating().name()))) {
            throw new InvalidRatingException("Invalid rating. The rating should be one of the following: PENDING, COMPLETED, FAILED, REFUNDED. Please verify the details and try again.");
        }
        return car;
    }

}
