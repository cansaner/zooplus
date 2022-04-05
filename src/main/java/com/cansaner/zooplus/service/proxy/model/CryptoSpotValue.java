package com.cansaner.zooplus.service.proxy.model;

/**
 * Created by cansaner on 05/04/22.
 */
public class CryptoSpotValue {
    private String base;
    private String currency;
    private String amount;

    public String getBase() {
        return base;
    }

    public CryptoSpotValue setBase(String base) {
        this.base = base;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public CryptoSpotValue setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public CryptoSpotValue setAmount(String amount) {
        this.amount = amount;
        return this;
    }
}
