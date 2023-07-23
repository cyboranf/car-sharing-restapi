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
import com.example.carental.validation.BookingValidator;
import com.google.api.services.calendar.Calendar;
import org.springframework.stereotype.Service;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final BookingValidator bookingValidator;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final Calendar calendarService;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper, BookingValidator bookingValidator, CarRepository carRepository, UserRepository userRepository, Calendar calendarService) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.bookingValidator = bookingValidator;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.calendarService = calendarService;
    }

    /**
     * @param carId
     * @param bookingRequestDTO
     * @return DTO of new booking
     */
    public BookingResponseDTO createBooking(Long carId, BookingRequestDTO bookingRequestDTO) {
        User user = bookingValidator.findUserToBookValidation(bookingRequestDTO.getUserId());
        Car car = bookingValidator.findCarToRentValidation(carId);

        Booking booking = bookingMapper.fromDTO(bookingRequestDTO);
        booking.setUser(user);
        booking.setCar(car);

        // Create an event in Google Calendar
        Event event = new Event()
                .setSummary("Booking for car " + carId)
                .setDescription("Booking by user " + bookingRequestDTO.getUserId());

        DateTime startDateTime = new DateTime(Date.from(bookingRequestDTO.getStartDate().atZone(ZoneId.systemDefault()).toInstant()));
        EventDateTime start = new EventDateTime().setDateTime(startDateTime);
        event.setStart(start);

        DateTime endDateTime = new DateTime(Date.from(bookingRequestDTO.getEndDate().atZone(ZoneId.systemDefault()).toInstant()));
        EventDateTime end = new EventDateTime().setDateTime(endDateTime);
        event.setEnd(end);

        String calendarId = "primary";
        try {
            event = calendarService.events().insert(calendarId, event).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        booking.setCalendarEventId(event.getId());

        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDTO(savedBooking);
    }

    /**
     * @param userId
     * @return List of all DTO bookings of user with id = userId
     */
    public List<BookingResponseDTO> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(bookingMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param bookingId
     * @return DTO of booking with id = bookingId
     */
    public BookingResponseDTO getBookingDetails(Long bookingId) {
        Booking booking = bookingValidator.getByIdValidation(bookingId);

        return bookingMapper.toDTO(booking);
    }

    /**
     * @param bookingId
     * @param bookingRequestDTO
     * @return DTO of updated Booking
     */
    public BookingResponseDTO updateBooking(Long bookingId, BookingRequestDTO bookingRequestDTO) {
        Booking booking = bookingValidator.getByIdValidation(bookingId);
        bookingValidator.findUserToBookValidation(bookingRequestDTO.getUserId());
        bookingValidator.findCarToRentValidation(bookingRequestDTO.getCarId());

        booking = bookingMapper.fromDTO(bookingRequestDTO);

        Booking updatedBooking = bookingRepository.save(booking);

        return bookingMapper.toDTO(updatedBooking);
    }

    /**
     * @param bookingId
     * @return DTO of deleted Booking
     */
    public BookingResponseDTO cancelBooking(Long bookingId) {
        Booking booking = bookingValidator.getByIdValidation(bookingId);
        bookingRepository.deleteById(bookingId);
        return bookingMapper.toDTO(booking);
    }
}
