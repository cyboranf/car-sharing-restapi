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
import com.google.api.services.calendar.Calendar;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
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
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    private final Calendar calendarService;

    public BookingService(BookingRepository bookingRepository, CarRepository carRepository, UserRepository userRepository, Calendar calendarService) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.calendarService = calendarService;
    }

    public BookingResponseDTO createBooking(Long carId, BookingRequestDTO bookingRequestDTO) {
        User user = userRepository.findById(bookingRequestDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with id: " + bookingRequestDTO.getUserId()));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("Car not found with id: " + carId));

        Booking booking = BookingMapper.toEntity(bookingRequestDTO);
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
