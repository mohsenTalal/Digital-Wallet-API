package com.mohsen.walletapp.dtos.requestDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupDto {
    @NotNull
    @Schema(example = "Mohsen")
    private String firstName;

    @NotNull @Schema(example = "Alenazi")
    private String lastName;

    @NotNull @Schema(example = "talal1mohsen@gmail.com")
    private String email;

    @NotNull @Schema(example = "KSA")
    private String country;

    @NotNull @Schema(example = "Riyadh")
    private String state;

    @NotNull @Schema(example = "10, Riyadh East, 00000")
    private String homeAddress;

    @NotNull @Schema(example = "1234")
    private String password;

    @NotNull @Schema(example = "05xxxxxxxx")
    private String phoneNumber;
}
