package com.cansaner.zooplus.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class CryptocurrencyPriceRequest {
    @NotEmpty(message = "Crypto code is mandatory")
    private String cryptoCode;

    @Pattern(regexp = "(\\b25[0-5]|\\b2[0-4][0-9]|\\b[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}",
            message = "ip address should be ipv4")
    private String ipAddress;

    public CryptocurrencyPriceRequest() {}

    public CryptocurrencyPriceRequest(String cryptoCode, String ipAddress) {
        this.cryptoCode = cryptoCode;
        this.ipAddress = ipAddress;
    }

    public void setCryptoCode(String cryptoCode) {
        this.cryptoCode = cryptoCode;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCryptoCode() {
        return cryptoCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
