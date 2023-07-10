package com.mohsen.walletapp.dtos.requestDtos;

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
public class TrxQueryRequestDto {

    @Schema(example = "42680")
    private String profile_id;

    @Schema(example = "TST2319000028864")
    private String tran_ref;
}
