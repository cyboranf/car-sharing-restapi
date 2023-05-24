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

    @PostMapping("/{carId}/bookings")
    public ResponseEntity<BookingResponseDTO> createBooking(@PathVariable Long carId, @RequestBody BookingRequestDTO bookingRequestDTO) {
        return ResponseEntity.ok(bookingService.createBooking(carId, bookingRequestDTO));
    }

    @GetMapping("/users/{userId}/bookings")
    public ResponseEntity<List<BookingResponseDTO>> getUserBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getUserBookings(userId));
    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingDetails(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.getBookingDetails(bookingId));
    }

    @PutMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long bookingId, @RequestBody BookingRequestDTO bookingRequestDTO) {
        return ResponseEntity.ok(bookingService.updateBooking(bookingId, bookingRequestDTO));
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok().build();
    }
}
