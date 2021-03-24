package com.crypto.portal.cryptoportal.entity;
//currency_wallets

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currency_wallets")
public class CurrencyWalletsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long currency_id;
    private String wallet_name;
    private String wallet_url;

    @OneToMany(targetEntity = CurrencyEntity.class)
    @JoinColumn(name = "currency_id")
    private List<CurrencyEntity> currency;

}

