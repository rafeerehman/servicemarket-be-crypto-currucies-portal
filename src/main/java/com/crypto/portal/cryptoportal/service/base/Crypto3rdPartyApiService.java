package com.crypto.portal.cryptoportal.service.base;

import com.crypto.portal.cryptoportal.dto.Crypto3rdPartyApiCurrenciesDto;
import com.crypto.portal.cryptoportal.response.BaseResponse;

import java.util.List;

public interface Crypto3rdPartyApiService {

    BaseResponse saveCurrenciesInfo(List<Crypto3rdPartyApiCurrenciesDto> infoList);

}
