package com.crypto.portal.cryptoportal.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currency")
public class CurrencyEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String BlockChain;
    @Column(unique = true)
    private String Symbol;
    private String MarketCap;
    private String Price;
    private String CircSupply;
    @Column(unique = true)
    private String CoinName;
    private String rank;
    private String logo_url;
    private String date_added;
    private String Description;
}
