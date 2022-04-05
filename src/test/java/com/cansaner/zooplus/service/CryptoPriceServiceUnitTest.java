package com.cansaner.zooplus.service;

import com.cansaner.zooplus.service.model.Money;
import com.cansaner.zooplus.service.proxy.CoinbaseApiService;
import com.cansaner.zooplus.service.proxy.exception.GeneralCoinbaseApiSideException;
import com.cansaner.zooplus.service.proxy.exception.IpApiSideException;
import com.cansaner.zooplus.service.proxy.model.CryptoSpotValue;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by cansaner on 05/04/22.
 */
public class CryptoPriceServiceUnitTest {

    private static CryptoPriceServiceImpl cryptoPriceService;
    private static CoinbaseApiService mockedCoinbaseApiService;

    @BeforeClass
    public static void setUpIpResolvingServiceInstance() {
        mockedCoinbaseApiService = mock(CoinbaseApiService.class);
        cryptoPriceService = new CryptoPriceServiceImpl(mockedCoinbaseApiService);
    }

    @Test
    public void whenCalledResolve_thenCorrect() {
        String cryptoCode = "SOL";
        String currencyCode = "EUR";
        CryptoSpotValue spotValue = new CryptoSpotValue()
                .setBase(cryptoCode)
                .setCurrency(currencyCode)
                .setAmount("119.4");

        Money expectedResult = new Money()
                .setAmount(new BigDecimal(spotValue.getAmount()))
                .setCurrency("EUR");

        when(mockedCoinbaseApiService.getSpotValue(cryptoCode, currencyCode)).thenReturn(spotValue);

        Money actualResult = cryptoPriceService.getCryptoUnitValue(cryptoCode, currencyCode);
        assertThat(actualResult.getAmount()).isEqualTo(expectedResult.getAmount());
        assertThat(actualResult.getCurrency()).isEqualTo(expectedResult.getCurrency());
    }

    @Test(expected = IpApiSideException.class)
    public void whenCalledResolveAndIpApiFailed_thenCorrect() {
        String cryptoCode = "SOLRRATTTAU";
        String currencyCode = "EUR";
        CryptoSpotValue spotValue = new CryptoSpotValue()
                .setBase(cryptoCode)
                .setCurrency(currencyCode)
                .setAmount("119.4");

        Money expectedResult = new Money()
                .setAmount(new BigDecimal(spotValue.getAmount()))
                .setCurrency("EUR");

        when(mockedCoinbaseApiService.getSpotValue(cryptoCode, currencyCode)).thenThrow(new GeneralCoinbaseApiSideException());

        cryptoPriceService.getCryptoUnitValue(cryptoCode, currencyCode);
    }
}
