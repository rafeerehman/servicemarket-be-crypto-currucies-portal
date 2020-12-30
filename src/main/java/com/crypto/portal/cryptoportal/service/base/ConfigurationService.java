package com.crypto.portal.cryptoportal.service.base;

import com.crypto.portal.cryptoportal.entity.ConfigurationEntity;

import java.util.Map;

public interface ConfigurationService {
    Map updateConstants();
    ConfigurationEntity findConstantsByCode(String code);
}
