package com.crypto.portal.cryptoportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CryptoNamesApiJsonResponse {

    private String currency;
    private String rate;
    private String timestamp;


}
