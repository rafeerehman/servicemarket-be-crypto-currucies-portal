package com.crypto.portal.cryptoportal.business.base;

import com.crypto.portal.cryptoportal.dto.Crypto3rdPartyApiInfo;
import com.crypto.portal.cryptoportal.dto.CurrencyInfoDto;
import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;

import java.util.List;

public interface Crypto3rdPartyApiBusiness {

    BaseResponse getCryptoCurrencies(BaseRequest request);
    List<Crypto3rdPartyApiInfo> getCryptoInfo(BaseRequest request);
}
