package com.example.carental.service;

import com.example.carental.dto.booking.BookingRequestDTO;
import com.example.carental.dto.booking.BookingResponseDTO;
import com.example.carental.mapper.BookingMapper;
import com.example.carental.model.Booking;
import com.example.carental.model.Car;
import com.example.carental.model.User;
import com.example.carental.repository.BookingRepository;
import com.example.carental.repository.CarRepository;
import com.example.carental.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService {
    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, CarRepository carRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public BookingResponseDTO createBooking(Long carId, BookingRequestDTO bookingRequestDTO) {
        User user = userRepository.findById(bookingRequestDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with id: " + bookingRequestDTO.getUserId()));
        Car car = carRepository.findById(bookingRequestDTO.getCarId())
                .orElseThrow(() -> new NotFoundException("Car not found with id: " + bookingRequestDTO.getCarId()));

        Booking booking = BookingMapper.toEntity(bookingRequestDTO);
        booking.setUser(user);
        booking.setCar(car);

        Booking savedBooking = bookingRepository.save(booking);
        return BookingMapper.toDTO(savedBooking);
    }


    public List<BookingResponseDTO> getUserBookings(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookings.stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BookingResponseDTO getBookingDetails(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking not found with id: " + bookingId));
        return BookingMapper.toDTO(booking);
    }

    public BookingResponseDTO updateBooking(Long bookingId, BookingRequestDTO bookingRequestDTO) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking not found with ID: " + bookingId));
        BookingMapper.updateBookingFields(booking, bookingRequestDTO);
        Booking updatedBooking = bookingRepository.save(booking);
        return BookingMapper.toDTO(updatedBooking);
    }

    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
