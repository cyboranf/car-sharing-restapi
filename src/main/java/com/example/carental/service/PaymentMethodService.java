package com.example.carental.service;

import com.example.carental.dto.paymentMethod.PaymentMethodRequestDTO;
import com.example.carental.dto.paymentMethod.PaymentMethodResponseDTO;
import com.example.carental.exception.payment.InvalidPaymentMethodException;
import com.example.carental.mapper.PaymentMethodMapper;
import com.example.carental.model.PaymentMethod;
import com.example.carental.repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper paymentMethodMapper;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository, PaymentMethodMapper paymentMethodMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentMethodMapper = paymentMethodMapper;
    }

    /**
     * @param requestDTO
     * @return DTO of new PaymentMethod
     */
    public PaymentMethodResponseDTO createPaymentMethod(PaymentMethodRequestDTO requestDTO) {
        PaymentMethod paymentMethod = paymentMethodMapper.fromDTO(requestDTO);

        PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        return paymentMethodMapper.toDTO(savedPaymentMethod);
    }

    /**
     * @return List of DTO of all PaymentMethods
     */
    public List<PaymentMethodResponseDTO> getAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
        return paymentMethods.stream()
                .map(paymentMethodMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param id
     * @param requestDTO
     * @return DTO of updated PaymentMethod
     */
    public PaymentMethodResponseDTO updatePaymentMethod(Long id, PaymentMethodRequestDTO requestDTO) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new InvalidPaymentMethodException("Can not found Payment Method with id =" + id));

        paymentMethod.setMethod(requestDTO.getMethod());
        PaymentMethod updatedPaymentMethod = paymentMethodRepository.save(paymentMethod);

        return paymentMethodMapper.toDTO(updatedPaymentMethod);
    }

    /**
     * @param id
     * @return DTO of deleted PaymentMethod
     */
    public PaymentMethodResponseDTO deletePaymentMethod(Long id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new InvalidPaymentMethodException("Can not found Payment Method with id = " + id));
        paymentMethodRepository.delete(paymentMethod);
        return paymentMethodMapper.toDTO(paymentMethod);
    }

    /**
     * @param paymentMethodId
     * @return DTO of PaymentMethod with id = paymentMethodId
     */
    public PaymentMethodResponseDTO getPaymentMethodById(Long paymentMethodId) {
        return paymentMethodMapper.toDTO(paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new InvalidPaymentMethodException("Payment Method not found with id: " + paymentMethodId)));
    }
}
