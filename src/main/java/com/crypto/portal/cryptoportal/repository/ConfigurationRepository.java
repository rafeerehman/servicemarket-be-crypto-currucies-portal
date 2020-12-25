package com.crypto.portal.cryptoportal.repository;

import com.crypto.portal.cryptoportal.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Integer> {
    List<ConfigurationEntity> findAll();

    ConfigurationEntity findByCode(String code);
}
