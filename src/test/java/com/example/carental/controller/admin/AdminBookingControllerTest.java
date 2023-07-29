package com.example.carental.controller.admin;

import com.example.carental.dto.booking.BookingRequestDTO;
import com.example.carental.dto.booking.BookingResponseDTO;
import com.example.carental.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdminBookingControllerTest {

    @InjectMocks
    private AdminBookingController adminBookingController;

    @Mock
    private BookingService bookingService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserBookings() {
        BookingResponseDTO response1 = new BookingResponseDTO();
        BookingResponseDTO response2 = new BookingResponseDTO();
        List<BookingResponseDTO> responses = Arrays.asList(response1, response2);
        when(bookingService.getUserBookings(1L)).thenReturn(responses);

        ResponseEntity<List<BookingResponseDTO>> result = adminBookingController.getUserBookings(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }

    @Test
    public void testGetBookingDetails() {
        BookingResponseDTO response = new BookingResponseDTO();
        when(bookingService.getBookingDetails(1L)).thenReturn(response);

        ResponseEntity<BookingResponseDTO> result = adminBookingController.getBookingDetails(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testUpdateBooking() {
        BookingRequestDTO request = new BookingRequestDTO();
        BookingResponseDTO response = new BookingResponseDTO();
        when(bookingService.updateBooking(1L, request)).thenReturn(response);

        ResponseEntity<BookingResponseDTO> result = adminBookingController.updateBooking(1L, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testCancelBooking() {
        BookingResponseDTO response = new BookingResponseDTO();
        when(bookingService.cancelBooking(1L)).thenReturn(response);

        ResponseEntity<BookingResponseDTO> result = adminBookingController.cancelBooking(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testGetAllBookings() {
        BookingResponseDTO response1 = new BookingResponseDTO();
        BookingResponseDTO response2 = new BookingResponseDTO();
        List<BookingResponseDTO> responses = Arrays.asList(response1, response2);
        when(bookingService.getAllBookings()).thenReturn(responses);

        ResponseEntity<List<BookingResponseDTO>> result = adminBookingController.getAllBookings();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responses, result.getBody());
    }
}
