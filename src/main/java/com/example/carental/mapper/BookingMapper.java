package com.example.carental.mapper;

import com.example.carental.dto.booking.BookingRequestDTO;
import com.example.carental.dto.booking.BookingResponseDTO;
import com.example.carental.model.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {
    public static Booking toEntity(BookingRequestDTO bookingRequestDTO) {
        Booking booking = new Booking();
        booking.setStartDate(bookingRequestDTO.getStartDate());
        booking.setEndDate(bookingRequestDTO.getEndDate());
        return booking;
    }

    public static BookingResponseDTO toDTO(Booking booking) {
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
        bookingResponseDTO.setId(booking.getId());
        bookingResponseDTO.setStartDate(booking.getStartDate());
        bookingResponseDTO.setEndDate(booking.getEndDate());
        bookingResponseDTO.setUserId(booking.getUser() != null ? booking.getUser().getId() : null);
        bookingResponseDTO.setCarId(booking.getCar() != null ? booking.getCar().getId() : null);
        return bookingResponseDTO;
    }

    public static void updateBookingFields(Booking booking, BookingRequestDTO bookingRequestDTO) {
        booking.setStartDate(bookingRequestDTO.getStartDate());
        booking.setEndDate(bookingRequestDTO.getEndDate());
    }
}
