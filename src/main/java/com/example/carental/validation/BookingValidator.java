package com.example.carental.validation;

import com.example.carental.exception.booking.BookingNotFoundException;
import com.example.carental.exception.booking.CarForBookNotFoundException;
import com.example.carental.exception.booking.UserForBookNotFoundException;
import com.example.carental.model.Booking;
import com.example.carental.model.Car;
import com.example.carental.model.User;
import com.example.carental.repository.BookingRepository;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class BookingValidator {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    private final CarRepository carRepository;


    public BookingValidator(BookingRepository bookingRepository, UserRepository userRepository, CarRepository carRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    /**
     * @param userId
     * @return User or Exception
     */
    public User findUserToBookValidation(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserForBookNotFoundException("Can not found user with id = " + userId));
    }

    /**
     * @param carId
     * @return Car or Exception
     */
    public Car findCarToRentValidation(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new CarForBookNotFoundException("Can not found Car with id = " + carId));
    }

    /**
     * @param bookingId
     * @return Booking or Exception
     */
    public Booking getByIdValidation(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Can not found booking with id = " + bookingId));
    }
}
