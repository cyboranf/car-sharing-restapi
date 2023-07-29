package com.example.carental.service;

import com.example.carental.dto.booking.BookingRequestDTO;
import com.example.carental.dto.booking.BookingResponseDTO;
import com.example.carental.mapper.BookingMapper;
import com.example.carental.model.Booking;
import com.example.carental.model.Car;
import com.example.carental.model.User;
import com.example.carental.repository.BookingRepository;
import com.example.carental.validation.BookingValidator;
import com.google.api.services.calendar.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private BookingValidator bookingValidator;

    @Mock
    private Calendar calendarService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateBooking() {
        Long carId = 1L;
        Long userId = 1L;
        BookingRequestDTO request = new BookingRequestDTO();
        request.setStartDate(LocalDateTime.now());
        request.setEndDate(LocalDateTime.now().plusDays(1));
        request.setUserId(userId);
        request.setCarId(carId);

        User user = new User();
        user.setId(userId);

        Car car = new Car();
        car.setId(carId);

        Booking booking = new Booking();
        booking.setId(1L);
        booking.setUser(user);
        booking.setCar(car);
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());

        BookingResponseDTO responseDTO = new BookingResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setUserId(userId);
        responseDTO.setCarId(carId);
        responseDTO.setStartDate(request.getStartDate());
        responseDTO.setEndDate(request.getEndDate());

        when(bookingValidator.findUserToBookValidation(anyLong())).thenReturn(user);
        when(bookingValidator.findCarToRentValidation(anyLong())).thenReturn(car);
        when(bookingMapper.fromDTO(any(BookingRequestDTO.class))).thenReturn(booking);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(bookingMapper.toDTO(any(Booking.class))).thenReturn(responseDTO);

        Calendar.Events eventsMock = mock(Calendar.Events.class);
        Calendar.Events.Insert insertMock = mock(Calendar.Events.Insert.class);

        when(calendarService.events()).thenReturn(eventsMock);


    }


    @Test
    public void testGetUserBookings() {
        Long userId = 1L;
        Booking booking1 = new Booking();
        Booking booking2 = new Booking();
        when(bookingRepository.findByUserId(userId)).thenReturn(Arrays.asList(booking1, booking2));

        BookingResponseDTO bookingResponseDTO1 = new BookingResponseDTO();
        BookingResponseDTO bookingResponseDTO2 = new BookingResponseDTO();
        when(bookingMapper.toDTO(booking1)).thenReturn(bookingResponseDTO1);
        when(bookingMapper.toDTO(booking2)).thenReturn(bookingResponseDTO2);

        List<BookingResponseDTO> response = bookingService.getUserBookings(userId);

        assertEquals(2, response.size());
        verify(bookingRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void testGetBookingDetails() {
        Long bookingId = 1L;
        Booking booking = new Booking();
        when(bookingValidator.getByIdValidation(bookingId)).thenReturn(booking);

        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
        when(bookingMapper.toDTO(booking)).thenReturn(bookingResponseDTO);

        BookingResponseDTO response = bookingService.getBookingDetails(bookingId);

        assertEquals(bookingResponseDTO, response);
        verify(bookingValidator, times(1)).getByIdValidation(bookingId);
    }

    @Test
    public void testUpdateBooking() {
        Long bookingId = 1L;

        BookingRequestDTO request = new BookingRequestDTO();
        request.setStartDate(LocalDateTime.now());
        request.setEndDate(LocalDateTime.now().plusDays(1));
        request.setUserId(1L);
        request.setCarId(1L);

        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setStartDate(request.getStartDate());
        booking.setEndDate(request.getEndDate());

        BookingResponseDTO responseDTO = new BookingResponseDTO();
        responseDTO.setStartDate(request.getStartDate());
        responseDTO.setEndDate(request.getEndDate());

        when(bookingValidator.getByIdValidation(bookingId)).thenReturn(booking);
        when(bookingMapper.fromDTO(any(BookingRequestDTO.class))).thenReturn(booking);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(bookingMapper.toDTO(any(Booking.class))).thenReturn(responseDTO);

        BookingResponseDTO response = bookingService.updateBooking(bookingId, request);

        assertEquals(responseDTO, response);
    }

    @Test
    public void testCancelBooking() {
        Long bookingId = 1L;
        Booking booking = new Booking();
        booking.setId(bookingId);

        BookingResponseDTO responseDTO = new BookingResponseDTO();
        responseDTO.setId(bookingId);

        when(bookingValidator.getByIdValidation(bookingId)).thenReturn(booking);
        when(bookingMapper.toDTO(booking)).thenReturn(responseDTO);

        BookingResponseDTO response = bookingService.cancelBooking(bookingId);

        assertEquals(responseDTO, response);
        verify(bookingRepository, times(1)).deleteById(bookingId);
    }
}
