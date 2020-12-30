package com.crypto.portal.cryptoportal.business.impl;


import com.crypto.portal.cryptoportal.business.base.CryptoApiBusiness;
import com.crypto.portal.cryptoportal.dto.CryptoNamesApiJsonResponse;
import com.crypto.portal.cryptoportal.dto.CryptoRatesDto;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.response.CryptoRatesResponse;
import com.crypto.portal.cryptoportal.util.ConfigurationUtil;
import com.crypto.portal.cryptoportal.util.Constants;
import com.crypto.portal.cryptoportal.util.RestServiceUtility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class CryptoApiBusinessImpl implements CryptoApiBusiness {

    @Autowired
    RestServiceUtility utility;

    @Autowired
    ConfigurationUtil configurationUtil;


    @Override
    public BaseResponse getCryptoNames(BaseRequest request){

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");


        List response = (List) utility.callGetJson(configurationUtil.getMessage(Constants.GET_CRYPTO_NAMES_AND_RATES_NOMICS_API_URL)+configurationUtil.getMessage(Constants.NOMICS_API_ACCESS_KEY), ArrayList.class, header);
        List<CryptoNamesApiJsonResponse> jsonResponseList = null;
        List<String> cryptoList = new ArrayList<>();
        if(response != null){
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<CryptoNamesApiJsonResponse>>(){});
            for (CryptoNamesApiJsonResponse cryptoNamesApiJsonResponse: jsonResponseList) {
                cryptoList.add(cryptoNamesApiJsonResponse.getCurrency());
            }

            return  BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(cryptoList).build();

        }else{
            return  BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
        }


    }

    @Override
    public BaseResponse getCryptoRates(BaseRequest request){

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");

        List response = (List) utility.callGetJson(configurationUtil.getMessage(Constants.GET_CRYPTO_NAMES_AND_RATES_NOMICS_API_URL)+configurationUtil.getMessage(Constants.NOMICS_API_ACCESS_KEY), ArrayList.class, header);
        List<CryptoNamesApiJsonResponse> jsonResponseList = null;
        List<CryptoRatesDto> cryptoList = new ArrayList<>();
        if(response != null){
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<CryptoNamesApiJsonResponse>>(){});
            for (CryptoNamesApiJsonResponse cryptoRatesApiJsonResponse: jsonResponseList) {
                Double value = 0.0;
                if(cryptoRatesApiJsonResponse.getRate() != null){
                    value = Double.parseDouble(cryptoRatesApiJsonResponse.getRate());
                    value = value*161.5; //ToDo : extract current dollar rate every day with schedular
                }
                cryptoList.add(CryptoRatesDto.builder().currencyName(cryptoRatesApiJsonResponse.getCurrency()).currencyRateDollar(cryptoRatesApiJsonResponse.getRate()).currencyRatePkr(value.toString()).build());
            }

            CryptoRatesResponse cryptoRatesResponse = CryptoRatesResponse.builder().nameAndRate(cryptoList).date(LocalDate.now().toString()).build();

            return  BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(cryptoRatesResponse).build();

        }else{
            return  BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
        }
    }


}
