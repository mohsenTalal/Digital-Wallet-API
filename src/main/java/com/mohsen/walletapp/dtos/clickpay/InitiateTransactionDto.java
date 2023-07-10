package com.mohsen.walletapp.dtos.clickpay;

import com.mohsen.walletapp.dtos.requestDtos.customerDetailsDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.annotation.Nullable;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InitiateTransactionDto {

    private String id;
    private int profile_id;
    private String tran_type;
    private String tran_class;
    private String cart_id;
    private String cart_description;
    private String cart_currency;
    private Double cart_amount;
    private String callback;
    @JsonProperty("return")
    private String myreturn;
    @Nullable
    private String tran_ref;
    private customerDetailsDto customerDetails;
}
