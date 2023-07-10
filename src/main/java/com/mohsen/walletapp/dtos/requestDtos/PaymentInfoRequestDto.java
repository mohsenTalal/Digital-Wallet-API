package com.mohsen.walletapp.dtos.requestDtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.io.Serializable;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfoRequestDto implements Serializable {

    private String id;
    private String paymentMethod;
    private String cardType;
    private String cardScheme;
    private String paymentDescription;
    private float expiryMonth;
    private float expiryYear;
}