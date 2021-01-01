package com.crypto.portal.cryptoportal.controller;

import com.crypto.portal.cryptoportal.business.base.CryptoCurrencyBusiness;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cryptoCurrency")
public class CryptoCurrencyController {
    @Autowired
    CryptoCurrencyBusiness cryptoCurrencyBusiness;

    @GetMapping("/getAllExchangesCompanies")
    public ResponseEntity<BaseResponse> getAllExchangesCompanies(@Valid BaseRequest request){
        BaseResponse response = cryptoCurrencyBusiness.getExchangeCompanies(request);

        return ResponseEntity.ok(response);
    }

}
