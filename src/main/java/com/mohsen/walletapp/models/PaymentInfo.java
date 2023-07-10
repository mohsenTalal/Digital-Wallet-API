package com.mohsen.walletapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Builder
@Entity
@Table(name = "payment_info")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "card_type", insertable=false , updatable= false)
    private String cardType;
    @Column(name = "card_type")
    private String cardScheme;
    @Column(name = "payment_description")
    private String paymentDescription;
    @Column(name = "expiry_month")
    private float expiryMonth;
    @Column(name = "expiry_year")
    private float expiryYear;
}
