package com.baeldung.crud.service.proxy.model;

public class IPGeolocation {
  private String status;
  private String message;
  private String countryCode;
  private String currency;
  private String query;

  public String getStatus() {
    return status;
  }

  public IPGeolocation setStatus( String status ) {
    this.status = status;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public IPGeolocation setMessage( String message ) {
    this.message = message;
    return this;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public IPGeolocation setCountryCode( String countryCode ) {
    this.countryCode = countryCode;
    return this;
  }

  public String getCurrency() {
    return currency;
  }

  public IPGeolocation setCurrency( String currency ) {
    this.currency = currency;
    return this;
  }

  public String getQuery() {
    return query;
  }

  public IPGeolocation setQuery( String query ) {
    this.query = query;
    return this;
  }
}
