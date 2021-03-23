package com.crypto.portal.cryptoportal.controller;

import com.crypto.portal.cryptoportal.business.base.Crypto3rdPartyApiBusiness;
import com.crypto.portal.cryptoportal.business.base.CryptoApiBusiness;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/crypto")
public class CryptoApiController {

    @Autowired
    CryptoApiBusiness business;

    @Autowired
    Crypto3rdPartyApiBusiness cryptoBusiness3rdParty;

    @PostMapping("/names")
    public ResponseEntity<BaseResponse> getCryptoNames(@Valid @RequestBody BaseRequest request){

        return ResponseEntity.ok(business.getCryptoNames(request));

    }

    @PostMapping("/rates")
    public ResponseEntity<BaseResponse> getCryptoRates(@Valid @RequestBody BaseRequest request){

        return ResponseEntity.ok(business.getCryptoRates(request));

    }

    @PostMapping("/exchanges")
    public ResponseEntity<BaseResponse> getExchangesCompanies(@Valid @RequestBody BaseRequest request){

        return ResponseEntity.ok(business.getExchangeCompanies(request));

    }

    @PostMapping("/currencies")
    public ResponseEntity<BaseResponse> getCurrencies(@Valid @RequestBody BaseRequest request){

        return ResponseEntity.ok(cryptoBusiness3rdParty.getCryptoCurrencies(request));

    }

    @PostMapping(value = "/search/{key}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> getCurrencyInfoByValue(@Valid @RequestBody BaseRequest request,@PathVariable String key){

        return ResponseEntity.ok(business.getCryptoInfo(request,key));

    }

    @PostMapping(value = "/weeklyrate/{cryptoName}/{date}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> getCryptoWeeklyRate(@Valid @RequestBody BaseRequest request,@PathVariable String cryptoName,@PathVariable String date){

        return ResponseEntity.ok(business.getCryptoWeeklyRates(request,cryptoName,date));

    }


}
