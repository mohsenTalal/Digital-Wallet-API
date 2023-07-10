package com.mohsen.walletapp.dtos.responseDtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentLinkResponseDto {
    public String tran_ref;
    public String tran_type;
    public String cart_id;
    public String cart_description;
    public String cart_currency;
    public String cart_amount;
    public String tran_currency;
    public String tran_total;
    public String callback;
    @JsonProperty("return")
    public String myreturn;
    public String redirect_url;
    public int serviceId;
    public int profileId;
    public int merchantId;
    public String trace;
}
