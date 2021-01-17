package com.crypto.portal.cryptoportal.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyInfoResponse {
    private String description;
    private  String url;
}
