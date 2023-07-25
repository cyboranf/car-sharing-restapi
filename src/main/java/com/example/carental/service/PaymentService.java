package com.example.carental.service;

import com.example.carental.dto.payment.PaymentRequestDTO;
import com.example.carental.dto.payment.PaymentResponseDTO;
import com.example.carental.mapper.PaymentMapper;
import com.example.carental.model.Payment;
import com.example.carental.model.enums.PaymentStatus;
import com.example.carental.paypalconfig.PayPalClient;
import com.example.carental.repository.PaymentRepository;
import com.example.carental.validation.PaymentValidator;
import com.paypal.orders.Order;
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
    private final PayPalClient payPalClient;

    public PaymentService(PaymentRepository paymentRepository, PaymentValidator paymentValidator, PaymentMapper paymentMapper, PayPalClient payPalClient) {
        this.paymentRepository = paymentRepository;
        this.paymentValidator = paymentValidator;
        this.paymentMapper = paymentMapper;
        this.payPalClient = payPalClient;
    }

    /**
     * @param bookingId
     * @param paymentRequestDTO
     * @return DTO of new Payment
     */
    public PaymentResponseDTO makePayment(Long bookingId, PaymentRequestDTO paymentRequestDTO) {
        paymentValidator.makePaymentValidation(bookingId, paymentRequestDTO);

        // Create a PayPal order
        Order order = payPalClient.createOrder();

        Payment payment = paymentMapper.fromDTO(paymentRequestDTO);
        payment.setId(Long.valueOf(order.id())); // Store the PayPal order ID in the payment

        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.toDTO(savedPayment);
    }

    /**
     * @param paymentId
     * @return DTO of Payment with id = paymentId
     */
    public PaymentResponseDTO completePayment(Long paymentId) {
        Payment payment = paymentValidator.getByIdValidation(paymentId);

        // Capture the PayPal order
        Order order = payPalClient.captureOrder(String.valueOf(payment.getId()));

        // Update the payment status based on the PayPal order status
        payment.setStatus(PaymentStatus.valueOf(order.status()));
        Payment updatedPayment = paymentRepository.save(payment);

        return paymentMapper.toDTO(updatedPayment);
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
