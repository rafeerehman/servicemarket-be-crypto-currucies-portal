package com.example.cryptomodule.service.base;

import com.example.cryptomodule.entity.ConfigurationEntity;

import java.util.Map;

public interface ConfigurationService {
    Map updateConstants();
    ConfigurationEntity findConstantsByCode(String code);
}
