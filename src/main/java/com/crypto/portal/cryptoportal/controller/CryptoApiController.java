package com.crypto.portal.cryptoportal.controller;

import com.crypto.portal.cryptoportal.business.base.CryptoApiBusiness;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/crypto")
public class CryptoApiController {

    @Autowired
    CryptoApiBusiness business;

    @PostMapping("/names")
    public ResponseEntity<BaseResponse> getCryptoNames(@Valid @RequestBody BaseRequest request){

        return ResponseEntity.ok(business.getCryptoNames(request));

    }

    @PostMapping("/rates")
    public ResponseEntity<BaseResponse> getCryptoRates(@Valid @RequestBody BaseRequest request){

        return ResponseEntity.ok(business.getCryptoRates(request));

    }


}
