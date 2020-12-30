package com.crypto.portal.cryptoportal.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BaseRequest {

    @NotBlank
    private String transactionId;

}
