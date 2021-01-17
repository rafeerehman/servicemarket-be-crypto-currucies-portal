package com.crypto.portal.cryptoportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ExchangeCompaniesIconListJsonResponse {
    @JsonProperty("exchange_id")
    private String exchangeId;
    private String url;
}
