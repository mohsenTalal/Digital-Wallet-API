package com.mohsen.walletapp.dtos.requestDtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto implements Serializable {


    private String  id;
    private String tranRef;
    private float merchantId;
    private float profileId;
    private String cartId;
    private String cartDescription;
    private String cartCurrency;
    private BigDecimal cartAmount;
    private String tranCurrency;
    private String tranTotal;
    private String tranType;
    private String tranClass;
    private String ipnTrace;
    private customerDetailsDto customerDetailsDto;
    private PaymentResultRequestDto paymentResultRequestDto;
    private PaymentInfoRequestDto paymentInfoRequestDto;

}