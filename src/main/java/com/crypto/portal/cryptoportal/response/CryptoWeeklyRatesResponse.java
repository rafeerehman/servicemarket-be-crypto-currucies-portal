package com.crypto.portal.cryptoportal.response;

import com.crypto.portal.cryptoportal.dto.CryptoRatesDto;
import com.crypto.portal.cryptoportal.dto.CryptoWeeklyRatesDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CryptoWeeklyRatesResponse {

    private String currency;
    List<CryptoWeeklyRatesDto> timestamps;
    List<CryptoWeeklyRatesDto> prices;

}
