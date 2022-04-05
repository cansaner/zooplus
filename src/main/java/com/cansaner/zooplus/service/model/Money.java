package com.cansaner.zooplus.service.model;

import java.math.BigDecimal;

/**
 * Created by cansaner on 05/04/22.
 */
public class Money {
    private BigDecimal amount;
    private String currency;

    public BigDecimal getAmount() {
        return amount;
    }

    public Money setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public Money setCurrency(String currency) {
        this.currency = currency;
        return this;
    }
}
