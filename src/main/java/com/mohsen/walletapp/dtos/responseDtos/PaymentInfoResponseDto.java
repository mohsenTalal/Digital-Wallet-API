package com.mohsen.walletapp.dtos.responseDtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfoResponseDto implements Serializable {
    private Long id;
    private String paymentMethod;
    private String cardType;
    private String cardScheme;
    private String paymentDescription;
    private float expiryMonth;
    private float expiryYear;
}
