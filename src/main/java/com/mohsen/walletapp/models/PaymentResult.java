package com.mohsen.walletapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@Table(name = "payment_result")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "response_status")
    private String responseStatus;
    @Column(name = "response_code")
    private String responseCode;
    @Column(name = "response_message")
    private String responseMessage;
    @Column(name = "cvv_result")
    private String cvvResult;
    @Column(name = "avs_result")
    private String avsResult;
    @Column(name = "transaction_time")
    private String transactionTime;
}
