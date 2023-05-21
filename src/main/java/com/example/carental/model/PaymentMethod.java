package com.example.carental.model;

import com.example.carental.model.enums.PaymentMethodValue;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "payment_method")
@Data
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethodValue method;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Payment> payments;
}
