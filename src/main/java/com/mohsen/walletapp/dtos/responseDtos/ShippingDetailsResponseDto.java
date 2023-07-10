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
public class ShippingDetailsResponseDto implements Serializable {

    public String name;
    public String email;
    public String street1;
    public String city;
    public String state;
    public String country;
    public String zip;
}
