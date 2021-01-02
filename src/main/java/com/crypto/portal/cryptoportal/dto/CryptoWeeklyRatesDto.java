package com.crypto.portal.cryptoportal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CryptoWeeklyRatesDto {

    private  String  currency;
    private  String  timestamp;
    private  String  prices;
}
