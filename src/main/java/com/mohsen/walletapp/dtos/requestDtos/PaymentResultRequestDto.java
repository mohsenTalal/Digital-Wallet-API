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
public class PaymentResultRequestDto implements Serializable {

    private String id;
    private String responseStatus;
    private String responseCode;
    private String responseMessage;
    private String cvvResult;
    private String avsResult;
    private String transactionTime;
}
