package com.cansaner.zooplus.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CryptocurrencyPriceRequestUnitTest {

    @Test
    public void whenCalledGetCryptoCode_thenCorrect() {
        CryptocurrencyPriceRequest cryptocurrencyPriceRequest = new CryptocurrencyPriceRequest("BTC", null);

        assertThat(cryptocurrencyPriceRequest.getCryptoCode()).isEqualTo("BTC");
        assertThat(cryptocurrencyPriceRequest.getIpAddress()).isNull();
    }

    @Test
    public void whenCalledGetIpAddress_thenCorrect() {
        CryptocurrencyPriceRequest cryptocurrencyPriceRequest = new CryptocurrencyPriceRequest("BTC", "104.108.78.0");

        assertThat(cryptocurrencyPriceRequest.getIpAddress()).isEqualTo("104.108.78.0");
    }

    @Test
    public void whenCalledSetCryptoCode_thenCorrect() {
        CryptocurrencyPriceRequest cryptocurrencyPriceRequest = new CryptocurrencyPriceRequest("BTC", "");

        cryptocurrencyPriceRequest.setCryptoCode("DOGE");

        assertThat(cryptocurrencyPriceRequest.getCryptoCode()).isEqualTo("DOGE");
        assertThat(cryptocurrencyPriceRequest.getIpAddress()).isEqualTo("");
    }

    @Test
    public void whenCalledSetIpAddress_thenCorrect() {
        CryptocurrencyPriceRequest cryptocurrencyPriceRequest = new CryptocurrencyPriceRequest("BTC", "104.108.78.0");

        cryptocurrencyPriceRequest.setIpAddress("102.165.31.0");

        assertThat(cryptocurrencyPriceRequest.getIpAddress()).isEqualTo("102.165.31.0");
    }
}
