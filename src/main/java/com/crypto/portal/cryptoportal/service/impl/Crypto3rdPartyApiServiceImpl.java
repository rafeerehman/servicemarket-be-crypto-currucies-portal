package com.crypto.portal.cryptoportal.service.impl;

import com.crypto.portal.cryptoportal.dto.Crypto3rdPartyApiCurrenciesDto;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import com.crypto.portal.cryptoportal.service.base.Crypto3rdPartyApiService;
import com.crypto.portal.cryptoportal.util.ConfigurationUtil;
import com.crypto.portal.cryptoportal.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Crypto3rdPartyApiServiceImpl implements Crypto3rdPartyApiService {

    @Autowired
    ConfigurationUtil configurationUtil;

    @Override
    public BaseResponse saveCurrenciesInfo(List<Crypto3rdPartyApiCurrenciesDto> infoList) {


        System.out.println(infoList);

        return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
    }
}
