package com.mohsen.walletapp.dtos.responseDtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mohsen.walletapp.models.CustomerDetails;
import com.mohsen.walletapp.models.PaymentInfo;
import com.mohsen.walletapp.models.PaymentResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto implements Serializable  {

    public Long  id;
    public String tranRef;
    public float merchantId;
    public float profileId;
    public String cartId;
    public String cartDescription;
    public String cartCurrency;
    public BigDecimal cartAmount;
    public String tranCurrency;
    public String tranTotal;
    public String tranType;
    public String tranClass;
    public String ipnTrace;
    public CustomerDetailsResponseDto customerDetailsResponseDto;
    public PaymentResultResponseDto paymentResultResponseDto;
    public PaymentInfoResponseDto paymentInfoResponseDto;

}
