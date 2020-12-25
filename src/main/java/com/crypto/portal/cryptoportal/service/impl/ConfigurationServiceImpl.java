package com.crypto.portal.cryptoportal.service.impl;


import com.crypto.portal.cryptoportal.service.base.ConfigurationService;
import com.crypto.portal.cryptoportal.entity.ConfigurationEntity;
import com.crypto.portal.cryptoportal.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {


    @Autowired
    ConfigurationRepository constantsRepository;


    @Override
    public Map updateConstants() {

        Map<String, String> constants = new HashMap<>();

        List<ConfigurationEntity> entityList =  constantsRepository.findAll();
        for (ConfigurationEntity entity: entityList) {

            constants.put(entity.getCode(), entity.getMessageEnglish());
        }

        return constants;
    }

    @Override
    public ConfigurationEntity findConstantsByCode(String code) {
        return constantsRepository.findByCode(code);
    }
}

