package com.crypto.portal.cryptoportal.business.impl;

import com.crypto.portal.cryptoportal.business.base.Crypto3rdPartyApiBusiness;
import com.crypto.portal.cryptoportal.dto.Crypto3rdPartyApiCurrenciesDto;
import com.crypto.portal.cryptoportal.dto.Crypto3rdPartyApiInfo;
import com.crypto.portal.cryptoportal.dto.CurrencyInfoDto;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.response.CurrencyInfoResponse;
import com.crypto.portal.cryptoportal.util.ConfigurationUtil;
import com.crypto.portal.cryptoportal.util.Constants;
import com.crypto.portal.cryptoportal.util.RestServiceUtility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Crypto3rdPartyApiBusinessImpl implements Crypto3rdPartyApiBusiness {

    @Autowired
    RestServiceUtility utility;

    @Autowired
    ConfigurationUtil configurationUtil;

    @Override
    public BaseResponse getCryptoCurrencies(BaseRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");

        List response = (List) utility.callGetJson("https://api.nomics.com/v1/currencies/ticker?key=d0a7ba4aa83fa093b777e3085fa51a99", ArrayList.class, header);
        List<Crypto3rdPartyApiCurrenciesDto> jsonResponseList = null;
        List<String> cryptoList = new ArrayList<>();

        if (response != null) {
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<Crypto3rdPartyApiCurrenciesDto>>() {});

            List<Crypto3rdPartyApiInfo> currencyInfo = getCryptoInfo(request);

            System.out.println("response: " + jsonResponseList);

            for(Crypto3rdPartyApiCurrenciesDto currencyResponse : jsonResponseList) {

                for(Crypto3rdPartyApiInfo infoResponse : currencyInfo) {
                    if(infoResponse.getId().equals(currencyResponse.getId()))
                    {
                        currencyResponse.setDescription(infoResponse.getDescription());
                    }
                }

//                cryptoList.add(currencyInfoResponse.getDescription());
//                cryptoList.add(currencyInfoResponse.getWebsite_url());

            }


            CurrencyInfoResponse currencyInfoResponse = CurrencyInfoResponse.builder().description(cryptoList.get(0)).url(cryptoList.get(1)).build();
            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(currencyInfoResponse).build();

        }
        else{
            return  BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
        }
    }


    @Override
    public List<Crypto3rdPartyApiInfo> getCryptoInfo(BaseRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");

        List response = (List) utility.callGetJson("https://api.nomics.com/v1/currencies?key=d0a7ba4aa83fa093b777e3085fa51a99", ArrayList.class, header);
        List<Crypto3rdPartyApiInfo> jsonResponseList = null;

        if (response != null) {
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<Crypto3rdPartyApiInfo>>() {});

            return jsonResponseList;

        }
        else{
            return  null;
        }
    }
}
