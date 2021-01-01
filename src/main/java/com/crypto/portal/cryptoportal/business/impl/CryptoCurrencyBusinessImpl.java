package com.crypto.portal.cryptoportal.business.impl;

import com.crypto.portal.cryptoportal.business.base.CryptoCurrencyBusiness;
import com.crypto.portal.cryptoportal.dto.ExchangeCompaniesIconListJsonResponse;
import com.crypto.portal.cryptoportal.dto.ExchangeCompaniesListJsonResponse;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.util.ConfigurationUtil;
import com.crypto.portal.cryptoportal.util.Constants;
import com.crypto.portal.cryptoportal.util.RestServiceUtility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptoCurrencyBusinessImpl implements CryptoCurrencyBusiness{
    private final ConfigurationUtil configurationUtil;
    private final RestServiceUtility utility;

    @Autowired
    public CryptoCurrencyBusinessImpl(ConfigurationUtil configurationUtil, RestServiceUtility utility){
        this.configurationUtil = configurationUtil;
        this.utility = utility;
    }

    @Override
    public BaseResponse getExchangeCompanies(BaseRequest request) {
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");
        header.add("X-CoinAPI-Key", "3A6C0FDA-BD54-4D72-B96C-5A0152809198");

        List response = (List) utility.callGetJson("https://rest.coinapi.io/v1/exchanges", ExchangeCompaniesListJsonResponse.class, header);
        List<ExchangeCompaniesListJsonResponse> jsonResponseList = null;
        if(response !=null){

            jsonResponseList = mapper.convertValue(response, new TypeReference<List<ExchangeCompaniesListJsonResponse>>(){});

            BaseResponse baseResponse = getExchangeCompaniesIcon(request);
            for(ExchangeCompaniesListJsonResponse companiesResponse : jsonResponseList){
               // ExchangeCompaniesListJsonResponse companiesResponse = jsonResponseList.get(i);
                for(ExchangeCompaniesIconListJsonResponse companiesIconResponse: ((List<ExchangeCompaniesIconListJsonResponse>)baseResponse.getResponse())){
                    if(companiesResponse.getExchangeId().equals(companiesIconResponse.getExchangeId())){
                        companiesResponse.setIconUrl(companiesIconResponse.getUrl());
                      //  jsonResponseList.set(i,companiesResponse);
                    }
                }
            }
            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(jsonResponseList).build();
        }else{
            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
        }
    }

    @Override
    public BaseResponse getExchangeCompaniesIcon(BaseRequest request) {
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");
        header.add("X-CoinAPI-Key", "3A6C0FDA-BD54-4D72-B96C-5A0152809198");

        List response = (List) utility.callGetJson("https://rest.coinapi.io/v1/exchanges/icons/32", ExchangeCompaniesIconListJsonResponse.class, header);
        List<ExchangeCompaniesIconListJsonResponse> jsonResponseList = null;
        if(response !=null){

            jsonResponseList = mapper.convertValue(response, new TypeReference<List<ExchangeCompaniesIconListJsonResponse>>(){});
            BaseResponse companiesIconResponse =  BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(jsonResponseList).build();

         //   BaseResponse companiesResponse = getExchangeCompanies(request);

          //  (companiesResponse.getResponse())

            return companiesIconResponse;
        }else{
            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
        }
    }
}
