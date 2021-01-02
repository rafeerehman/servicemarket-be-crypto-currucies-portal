package com.crypto.portal.cryptoportal.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CurrencyInfoDto {

    String description;
    String website_url;


}
