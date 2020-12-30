package com.crypto.portal.cryptoportal.business.base;

import com.crypto.portal.cryptoportal.request.BaseRequest;
import com.crypto.portal.cryptoportal.response.BaseResponse;
import org.springframework.stereotype.Component;

public interface CryptoApiBusiness {

    BaseResponse getCryptoNames(BaseRequest request);
    BaseResponse getCryptoRates(BaseRequest request);
}
