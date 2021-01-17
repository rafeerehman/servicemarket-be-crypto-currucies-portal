package com.crypto.portal.cryptoportal.business.impl;


import com.crypto.portal.cryptoportal.business.base.CryptoApiBusiness;
import com.crypto.portal.cryptoportal.dto.CryptoNamesApiJsonResponse;
import com.crypto.portal.cryptoportal.dto.CryptoRatesDto;
import com.crypto.portal.cryptoportal.dto.CryptoWeeklyRatesDto;
import com.crypto.portal.cryptoportal.dto.CurrencyInfoDto;
import com.crypto.portal.cryptoportal.dto.ExchangeCompaniesIconListJsonResponse;
import com.crypto.portal.cryptoportal.dto.ExchangeCompaniesListJsonResponse;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.response.CryptoRatesResponse;
import com.crypto.portal.cryptoportal.response.CryptoWeeklyRatesResponse;
import com.crypto.portal.cryptoportal.response.CurrencyInfoResponse;
import com.crypto.portal.cryptoportal.response.ExchangeCompaniesResponse;
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
    public BaseResponse getCryptoNames(BaseRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");


        List response = (List) utility.callGetJson(configurationUtil.getMessage(Constants.GET_CRYPTO_NAMES_AND_RATES_NOMICS_API_URL) + configurationUtil.getMessage(Constants.NOMICS_API_ACCESS_KEY), ArrayList.class, header);
        List<CryptoNamesApiJsonResponse> jsonResponseList = null;
        List<String> cryptoList = new ArrayList<>();
        if (response != null) {
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<CryptoNamesApiJsonResponse>>() {
            });
            for (CryptoNamesApiJsonResponse cryptoNamesApiJsonResponse : jsonResponseList) {
                cryptoList.add(cryptoNamesApiJsonResponse.getCurrency());
            }

            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(cryptoList).build();

        } else {
            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
        }


    }

    @Override
    public BaseResponse getCryptoRates(BaseRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");

        List response = (List) utility.callGetJson(configurationUtil.getMessage(Constants.GET_CRYPTO_NAMES_AND_RATES_NOMICS_API_URL) + configurationUtil.getMessage(Constants.NOMICS_API_ACCESS_KEY), ArrayList.class, header);
        List<CryptoNamesApiJsonResponse> jsonResponseList = null;
        List<CryptoRatesDto> cryptoList = new ArrayList<>();
        if (response != null) {
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<CryptoNamesApiJsonResponse>>() {
            });
            for (CryptoNamesApiJsonResponse cryptoRatesApiJsonResponse : jsonResponseList) {
                Double value = 0.0;
                if (cryptoRatesApiJsonResponse.getRate() != null) {
                    value = Double.parseDouble(cryptoRatesApiJsonResponse.getRate());
                    value = value * 161.5; //ToDo : extract current dollar rate every day with schedular
                }
                cryptoList.add(CryptoRatesDto.builder().currencyName(cryptoRatesApiJsonResponse.getCurrency()).currencyRateDollar(cryptoRatesApiJsonResponse.getRate()).currencyRatePkr(value.toString()).build());
            }

            CryptoRatesResponse cryptoRatesResponse = CryptoRatesResponse.builder().nameAndRate(cryptoList).date(LocalDate.now().toString()).build();

            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(cryptoRatesResponse).build();

        } else {
            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
        }
    }

    @Override
    public BaseResponse getCryptoInfo(BaseRequest request, String cryptoName) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");

        List response = (List) utility.callGetJson("https://api.nomics.com/v1/currencies?key=d0a7ba4aa83fa093b777e3085fa51a99&ids=" + cryptoName, ArrayList.class, header);
        List<CurrencyInfoDto> jsonResponseList = null;
        List<String> cryptoList = new ArrayList<>();

        if (response != null) {
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<CurrencyInfoDto>>() {});


            System.out.println("response: " + jsonResponseList);

            for(CurrencyInfoDto currencyInfoResponse : jsonResponseList) {

               cryptoList.add(currencyInfoResponse.getDescription());
                cryptoList.add(currencyInfoResponse.getWebsite_url());
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
    public BaseResponse getCryptoWeeklyRates(BaseRequest request, String cryptoName, String date) {

        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");

        List response = (List) utility.callGetJson("https://api.nomics.com/v1/currencies/sparkline?key=d0a7ba4aa83fa093b777e3085fa51a99&ids=BTC&start=2019-11-25T00%3A00%3A00Z", CryptoWeeklyRatesResponse.class, header);
 //       List response = (List) utility.callGetJson("https://api.nomics.com/v1/currencies/sparkline?key=d0a7ba4aa83fa093b777e3085fa51a99&ids="+cryptoName+"&start="+date+"T00%3A00%3A00Z", ArrayList.class, header);
       // System.out.println("Url:"+"https://api.nomics.com/v1/currencies/sparkline?key=d0a7ba4aa83fa093b777e3085fa51a99&ids="+cryptoName+"&start="+date+"T00%3A00%3A00Z");
        System.out.println("initial response:"+response);
        List<CryptoWeeklyRatesResponse> jsonResponseList = null;
        List<CryptoWeeklyRatesDto> cryptoList = new ArrayList<>();
        if (response != null) {
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<CryptoWeeklyRatesResponse>>() {
            });
            for (CryptoWeeklyRatesResponse cryptoWeeklyRatesDto : jsonResponseList) {
//                Double value = 0.0;
//                if (cryptoRatesApiJsonResponse.getRate() != null) {
//                    value = Double.parseDouble(cryptoRatesApiJsonResponse.getRate());
//                    value = value * 161.5; //ToDo : extract current dollar rate every day with schedular
//                }
                cryptoList.add(CryptoWeeklyRatesDto.builder().currency(cryptoWeeklyRatesDto.getCurrency()).timestamp(String.valueOf(cryptoWeeklyRatesDto.getTimestamps())).prices(String.valueOf(cryptoWeeklyRatesDto.getPrices())).build());
            }
            System.out.println("response: "+cryptoList);
            CryptoWeeklyRatesResponse cryptoWeeklyRatesResponse = CryptoWeeklyRatesResponse.builder().timestamps(cryptoList).prices(cryptoList).build();

            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(cryptoWeeklyRatesResponse).build();

        } else {
            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
        }

    @Override
    public BaseResponse getExchangeCompanies(BaseRequest request) {
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");
        header.add("X-CoinAPI-Key", configurationUtil.getMessage(Constants.COIN_API_ACCESS_KEY));

        List jsonResponse = (List) utility.callGetJson(configurationUtil.getMessage(Constants.GET_EXCHANGE_COMPANIES_AND_WEBSITE_COIN_API_URL), ExchangeCompaniesListJsonResponse.class, header);
        List<ExchangeCompaniesListJsonResponse> jsonResponseList = null;
        if(jsonResponse !=null){

            jsonResponseList = mapper.convertValue(jsonResponse, new TypeReference<List<ExchangeCompaniesListJsonResponse>>(){});

            List<ExchangeCompaniesResponse> response = new ArrayList<>();
            List<ExchangeCompaniesIconListJsonResponse> iconsListJsonResponse = getExchangeCompaniesIcon(request);
            for(ExchangeCompaniesListJsonResponse companiesJsonResponse : jsonResponseList){
                for(ExchangeCompaniesIconListJsonResponse iconJsonResponse: iconsListJsonResponse){
                    if(companiesJsonResponse.getExchangeId().equals(iconJsonResponse.getExchangeId())){
                        response.add(ExchangeCompaniesResponse.builder()
                                .exchangeId(companiesJsonResponse.getExchangeId())
                                .name(companiesJsonResponse.getName())
                                .website(companiesJsonResponse.getWebsite())
                                .dataStart(companiesJsonResponse.getDataStart())
                                .iconUrl(iconJsonResponse.getUrl())
                                .build());
                    }
                }
            }
            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(response).build();
        }else{
            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
        }
    }

    public List<ExchangeCompaniesIconListJsonResponse> getExchangeCompaniesIcon(BaseRequest request) {
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");
        header.add("X-CoinAPI-Key", configurationUtil.getMessage(Constants.COIN_API_ACCESS_KEY));

        List response = (List) utility.callGetJson(configurationUtil.getMessage(Constants.GET_EXCHANGE_COMPANIES_ICONS_COIN_32X32_API_URL), ExchangeCompaniesIconListJsonResponse.class, header);
        List<ExchangeCompaniesIconListJsonResponse> jsonResponseList = null;
        if(response !=null){
            jsonResponseList = mapper.convertValue(response, new TypeReference<List<ExchangeCompaniesIconListJsonResponse>>(){});
            return jsonResponseList;
        }else{
            return null;
        }
    }
    }

}
