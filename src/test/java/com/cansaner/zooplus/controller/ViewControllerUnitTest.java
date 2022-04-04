package com.cansaner.zooplus.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.cansaner.zooplus.dto.CryptocurrencyPriceRequest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public class ViewControllerUnitTest {

    private static ViewController viewController;
    private static BindingResult mockedBindingResult;
    private static Model mockedModel;

    @BeforeClass
    public static void setUpUserControllerInstance() {
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        viewController = new ViewController();
    }

    @Test
    public void whenCalledRoot_thenCorrect() {
        assertThat(viewController.showRoot(mockedModel)).isEqualTo("index");
    }

    @Test
    public void whenCalledIndex_thenCorrect() {
        assertThat(viewController.showIndex(mockedModel)).isEqualTo("index");
    }
    
    @Test
    public void whenCalledaddUserAndValidCryptocurrencyPriceRequest_thenCorrect() {
        CryptocurrencyPriceRequest cryptocurrencyPriceRequest = new CryptocurrencyPriceRequest("BTC", "104.108.78.0");

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(viewController.showCryptoUnitPrice(cryptocurrencyPriceRequest, mockedBindingResult, mockedModel)).isEqualTo("index");
    }

    @Test
    public void whenCalledaddUserAndInValidCryptocurrencyPriceRequest_thenCorrect() {
        CryptocurrencyPriceRequest cryptocurrencyPriceRequest = new CryptocurrencyPriceRequest("BTC", "104.108.78.0");

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(viewController.showCryptoUnitPrice(cryptocurrencyPriceRequest, mockedBindingResult, mockedModel)).isEqualTo("index");
    }
}
