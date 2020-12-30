package com.crypto.portal.cryptoportal.response;

import com.crypto.portal.cryptoportal.dto.CryptoRatesDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class CryptoRatesResponse {

    private String date;
    List<CryptoRatesDto> nameAndRate;

}
