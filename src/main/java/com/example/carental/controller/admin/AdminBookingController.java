package com.example.carental.controller.admin;

import com.example.carental.dto.booking.BookingRequestDTO;
import com.example.carental.dto.booking.BookingResponseDTO;
import com.example.carental.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/booking")
public class AdminBookingController {
    private final BookingService bookingService;

    public AdminBookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * @param userId
     * @return DTO of all user with id = @param bookings
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getUserBookings(@PathVariable Long userId) {
        List<BookingResponseDTO> bookings = bookingService.getUserBookings(userId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    /**
     * @param bookingId
     * @return DTO of booking with id = @param
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> getBookingDetails(@PathVariable Long bookingId) {
        BookingResponseDTO booking = bookingService.getBookingDetails(bookingId);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    /**
     * @param bookingId
     * @param bookingRequestDTO
     * @return DTO of updated Booking with id = @param
     */
    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long bookingId, @RequestBody BookingRequestDTO bookingRequestDTO) {
        BookingResponseDTO booking = bookingService.updateBooking(bookingId, bookingRequestDTO);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    /**
     * @param bookingId
     * @return DTO of deleted Booking with id = @param
     */
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable Long bookingId) {
        BookingResponseDTO booking = bookingService.cancelBooking(bookingId);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    /**
     * @return All bookings in car-sharing history.
     */
    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        List<BookingResponseDTO> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
}
