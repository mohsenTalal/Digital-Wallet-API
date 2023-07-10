package com.mohsen.walletapp.dtos.requestDtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransferDto implements Serializable {

    @Schema(example = "talal1mohsen@outlookc.com")
    private String email;

    @NotNull
    @Min(value = 0)
    @Schema(example = "15.75")
    private BigDecimal amount;

    @Schema(example = "For party")
    private String purpose;

    @Schema(example = "from my saving money")
    private String notes;
}
