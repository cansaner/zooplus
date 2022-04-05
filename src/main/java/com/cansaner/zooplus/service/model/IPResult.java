package com.baeldung.crud.service.model;

public class IPResult {
  private String countryCode;
  private String currency;

  public String getCountryCode() {
    return countryCode;
  }

  public IPResult setCountryCode(String countryCode ) {
    this.countryCode = countryCode;
    return this;
  }

  public String getCurrency() {
    return currency;
  }

  public IPResult setCurrency(String currency ) {
    this.currency = currency;
    return this;
  }
}
