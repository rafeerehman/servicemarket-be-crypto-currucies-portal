package com.crypto.portal.cryptoportal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CryptoRatesDto {

    private String currencyName;
    private String currencyRateDollar;
    private String currencyRatePkr;

}
