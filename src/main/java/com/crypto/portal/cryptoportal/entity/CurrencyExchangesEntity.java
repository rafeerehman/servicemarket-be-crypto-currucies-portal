package com.crypto.portal.cryptoportal.entity;

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
@Table(name = "currency_exchanges")
public class CurrencyExchangesEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long currency_id;
    private String exchange_name;
    private String exchange_url;



    @OneToMany(targetEntity = CurrencyEntity.class)
    @JoinColumn(name = "currency_id")
    private List<CurrencyEntity> currency;


}
