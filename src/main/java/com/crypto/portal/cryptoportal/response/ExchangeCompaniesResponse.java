package com.crypto.portal.cryptoportal.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExchangeCompaniesResponse {
    private String exchangeId;
    private String website;
    private String name;
    private String dataStart;
    private String iconUrl;
}
