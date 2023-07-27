package com.example.carental.controller;

import com.example.carental.dto.booking.BookingRequestDTO;
import com.example.carental.dto.booking.BookingResponseDTO;
import com.example.carental.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * @param carId
     * @param bookingRequestDTO
     * @return DTO of new booking
     */
    @PostMapping("/{carId}/bookings")
    public ResponseEntity<BookingResponseDTO> createBooking(@PathVariable Long carId, @RequestBody BookingRequestDTO bookingRequestDTO) {
        return ResponseEntity.ok(bookingService.createBooking(carId, bookingRequestDTO));
    }

    /**
     * @param userId
     * @return DTO of all Bookings of User with id = @param
     */
    @GetMapping("/users/{userId}/bookings")
    public ResponseEntity<List<BookingResponseDTO>> getUserBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getUserBookings(userId));
    }

    /**
     * @param bookingId
     * @return DTO of Booking with id = @param
     */
    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingDetails(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingDetails(bookingId));
    }

    /**
     * @param bookingId
     * @param bookingRequestDTO
     * @return DTO of updated Booking with id = @param
     */
    @PutMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long bookingId, @RequestBody BookingRequestDTO bookingRequestDTO) {
        return ResponseEntity.ok(bookingService.updateBooking(bookingId, bookingRequestDTO));
    }
}
