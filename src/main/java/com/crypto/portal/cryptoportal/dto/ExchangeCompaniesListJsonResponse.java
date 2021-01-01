package com.crypto.portal.cryptoportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ExchangeCompaniesListJsonResponse {
    @JsonProperty("exchange_id")
    private String exchangeId;
    private String website;
    private String name;
    @JsonProperty("data_start")
    private String dataStart;
    private String iconUrl;
}
