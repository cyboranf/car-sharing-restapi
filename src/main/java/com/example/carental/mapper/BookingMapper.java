package com.example.carental.mapper;

import com.example.carental.model.Booking;
import com.example.carental.dto.booking.BookingRequestDTO;
import com.example.carental.dto.booking.BookingResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingResponseDTO toDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setStartDate(booking.getStartDate());
        dto.setEndDate(booking.getEndDate());
        dto.setUserId(booking.getUser().getId());
        dto.setCarId(booking.getCar().getId());
        return dto;
    }

    public Booking fromDTO(BookingRequestDTO bookingRequestDTO) {
        Booking booking = new Booking();
        booking.setStartDate(bookingRequestDTO.getStartDate());
        booking.setEndDate(bookingRequestDTO.getEndDate());
        return booking;
    }
}
