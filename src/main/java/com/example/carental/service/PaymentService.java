package com.example.carental.service;

import com.example.carental.dto.payment.PaymentRequestDTO;
import com.example.carental.dto.payment.PaymentResponseDTO;
import com.example.carental.mapper.PaymentMapper;
import com.example.carental.model.Payment;
import com.example.carental.repository.PaymentRepository;
import com.example.carental.validation.PaymentValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentValidator paymentValidator;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentValidator paymentValidator, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentValidator = paymentValidator;
        this.paymentMapper = paymentMapper;
    }

    /**
     * @param bookingId
     * @param paymentRequestDTO
     * @return DTO of new Payment
     */
    public PaymentResponseDTO makePayment(Long bookingId, PaymentRequestDTO paymentRequestDTO) {
        paymentValidator.makePaymentValidation(bookingId, paymentRequestDTO);

        Payment payment = paymentMapper.fromDTO(paymentRequestDTO);
        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.toDTO(savedPayment);
    }

    /**
     * @param userId
     * @return DTO of all Users with id = userId Payments
     */
    public List<PaymentResponseDTO> getUserPayments(Long userId) {
        List<Payment> payments = paymentRepository.findByUserId(userId);
        return payments.stream()
                .map(paymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param paymentId
     * @return DTO of Payment with id = paymentId
     */
    public PaymentResponseDTO getPaymentDetails(Long paymentId) {
        Payment payment = paymentValidator.getByIdValidation(paymentId);
        return paymentMapper.toDTO(payment);
    }
}
