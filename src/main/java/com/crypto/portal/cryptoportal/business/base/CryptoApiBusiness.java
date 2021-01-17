package com.crypto.portal.cryptoportal.business.base;

import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

public interface CryptoApiBusiness {

    BaseResponse getCryptoNames(BaseRequest request);
    BaseResponse getCryptoRates(BaseRequest request);
    BaseResponse getExchangeCompanies(BaseRequest request);
    BaseResponse getCryptoInfo(BaseRequest request,String cryptoName);
    BaseResponse getCryptoWeeklyRates(BaseRequest request,String cryptoName, String date);
}
