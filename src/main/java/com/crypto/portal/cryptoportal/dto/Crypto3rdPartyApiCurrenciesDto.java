package com.crypto.portal.cryptoportal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crypto3rdPartyApiCurrenciesDto {

    private String currency;
    private String symbol;
    private String market_cap;
    private String price;
    private String circulating_supply;
    private String rank;
    private String logo_url;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date first_candle;

    private String high;
    private String id;
    private String description;

    @JsonProperty("1d")
    private PriceChange oneDay;
    @JsonProperty("7d")
    private PriceChange weekly;
    @JsonProperty("30d")
    private PriceChange monthly;
    @JsonProperty("365d")
    private PriceChange yearly;

}

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
class PriceChange{
    private String price_change;
}
