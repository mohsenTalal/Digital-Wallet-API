package com.mohsen.walletapp.dtos.clickpay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @Schema(example = "iban")
    private String type;
    private String account_name;
    @Schema(example = "Mohsen Talal")
    private String name;
    @Schema(example = "0001234567")
    private String account_number;
    @Schema(example = "SA03 80")
    private String bank_code;
    @Schema(example = "SAR")
    private String currency;
    private String bank_id;

}
