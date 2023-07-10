package com.mohsen.walletapp.dtos.responseDtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class TrxQueryResponseDto implements Serializable {

    public String tran_ref;
    public String cart_id;
    public String cart_description;
    public String cart_currency;
    public String cart_amount;
    public CustomerDetailsResponseDto customerDetailsResponseDto;
    public PaymentResultResponseDto paymentResultResponseDto;
    public PaymentInfoResponseDto paymentInfoResponseDto;
    public ShippingDetailsResponseDto shippingDetailsResponseDto;
}
