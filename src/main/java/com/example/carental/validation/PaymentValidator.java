package com.example.carental.validation;

import com.example.carental.dto.payment.PaymentRequestDTO;
import com.example.carental.exception.booking.BookingNotFoundException;
import com.example.carental.exception.payment.InvalidPaymentAmountException;
import com.example.carental.exception.payment.InvalidPaymentMethodException;
import com.example.carental.exception.payment.InvalidPaymentStatusException;
import com.example.carental.exception.payment.PaymentNotFoundException;
import com.example.carental.exception.rent.RentNotFoundException;
import com.example.carental.exception.user.UserNotFoundException;
import com.example.carental.model.Payment;
import com.example.carental.repository.*;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidator {
    private final PaymentRepository paymentRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public PaymentValidator(PaymentRepository paymentRepository, PaymentMethodRepository paymentMethodRepository, RentRepository rentRepository, UserRepository userRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    /**
     * @param bookingId
     * @param paymentRequestDTO
     */
    public void makePaymentValidation(Long bookingId, PaymentRequestDTO paymentRequestDTO) {
        bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException("Can not found booking with id = " + bookingId));

        if (paymentRequestDTO.getStatus() == null) {
            throw new InvalidPaymentStatusException("Payment status must not be null");
        }

        if (paymentRequestDTO.getAmount() == null || paymentRequestDTO.getAmount() <= 0) {
            throw new InvalidPaymentAmountException("Payment amount must be greater than 0");
        }

        if (paymentRequestDTO.getMethod() == null) {
            throw new InvalidPaymentMethodException("Payment method must not be null");
        }
        if (paymentRequestDTO.getPaymentMethodId() == null) {
            throw new InvalidPaymentMethodException("Payment method ID must not be null");
        }
        paymentMethodRepository.findById(paymentRequestDTO.getPaymentMethodId())
                .orElseThrow(() -> new InvalidPaymentMethodException("Can not found Payment Method with id = " + paymentRequestDTO.getPaymentMethodId()));
        if (paymentRequestDTO.getRentId() == null) {
            throw new RentNotFoundException("Rent ID must not be null");
        }
        rentRepository.findById(paymentRequestDTO.getRentId())
                .orElseThrow(() -> new RentNotFoundException("Can not found Rent with id = " + paymentRequestDTO.getRentId()));
        if (paymentRequestDTO.getUserId() == null) {
            throw new UserNotFoundException("User ID must not be null");
        }
        userRepository.findById(paymentRequestDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Can not found user with id =" + paymentRequestDTO.getUserId()));
    }

    /**
     * @param paymentId
     * @return Payment with id = paymentId or Exception
     */
    public Payment getByIdValidation(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("Can not found Payment with id = " + paymentId));
    }

}
