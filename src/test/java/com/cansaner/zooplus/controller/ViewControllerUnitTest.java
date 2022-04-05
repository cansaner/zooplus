package com.cansaner.zooplus.controller;

import com.cansaner.zooplus.dto.CryptocurrencyPriceRequest;
import com.cansaner.zooplus.service.CryptoPriceService;
import com.cansaner.zooplus.service.IpResolvingService;
import com.cansaner.zooplus.service.model.IpResult;
import com.cansaner.zooplus.service.model.Money;
import com.cansaner.zooplus.service.proxy.exception.GeneralCoinbaseApiSideException;
import com.cansaner.zooplus.service.proxy.exception.StatusFailedIpApiSideException;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ViewControllerUnitTest {

    private static ViewController viewController;
    private static IpResolvingService mockedIpResolvingService;
    private static CryptoPriceService mockedCryptoPriceService;
    private static BindingResult mockedBindingResult;
    private static Model mockedModel;
    private static HttpServletRequest mockedHttpServletRequest;
    private static final String BITCOIN_CODE = "BTC";
    private static final String GERMANY_IP = "104.108.78.0";
    private static final String UK_IP = "102.165.31.0";
    private static final String TURKEY_IP = "104.166.156.0";
    private static final String FRANCE_IP = "104.107.108.0";
    private static final String USA_IP = "101.36.98.0";
    private static final String UAE_IP = "103.244.134.0";

    @BeforeClass
    public static void setUpViewControllerInstance() {
        mockedIpResolvingService = mock(IpResolvingService.class);
        mockedCryptoPriceService = mock(CryptoPriceService.class);
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        mockedHttpServletRequest = mock(HttpServletRequest.class);
        viewController = new ViewController(mockedIpResolvingService, mockedCryptoPriceService);
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
    public void whenCalledCryptoUnitPriceAndInValidCryptocurrencyPriceRequest_thenCorrect() {
        CryptocurrencyPriceRequest cryptocurrencyPriceRequest = new CryptocurrencyPriceRequest(BITCOIN_CODE, GERMANY_IP);

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(viewController.showCryptoUnitPrice(cryptocurrencyPriceRequest, mockedBindingResult, mockedModel, mockedHttpServletRequest)).isEqualTo("index");
    }

    @Ignore
    //When not ignored and all services are run together, it collides with whenCalledCryptoUnitPriceAndAllServicesWorksFine_thenCorrect
    @Test
    public void whenCalledCryptoUnitPriceAndIpApiServicesFails_thenCorrect() {
        CryptocurrencyPriceRequest cryptocurrencyPriceRequest = new CryptocurrencyPriceRequest(BITCOIN_CODE, UK_IP);
        IpResult ipResult = new IpResult()
                .setCountryCode("GB")
                .setCurrency("GBP");
        Money unitValue = new Money()
                .setAmount(new BigDecimal("35495.26"))
                .setCurrency("GBP");


        when(mockedBindingResult.hasErrors()).thenReturn(false);
        when(mockedIpResolvingService.resolve(UK_IP)).thenThrow(new StatusFailedIpApiSideException("reserved range"));

        assertThat(viewController.showCryptoUnitPrice(cryptocurrencyPriceRequest, mockedBindingResult, mockedModel, mockedHttpServletRequest)).isEqualTo("redirect:/index");
    }

    @Ignore
    //When not ignored and all services are run together, it collides with whenCalledCryptoUnitPriceAndAllServicesWorksFine_thenCorrect
    @Test
    public void whenCalledCryptoUnitPriceAndCryptoApiServicesFails_thenCorrect() {
        CryptocurrencyPriceRequest cryptocurrencyPriceRequest = new CryptocurrencyPriceRequest(BITCOIN_CODE, UK_IP);
        IpResult ipResult = new IpResult()
                .setCountryCode("GB")
                .setCurrency("GBP");
        Money unitValue = new Money()
                .setAmount(new BigDecimal("35495.26"))
                .setCurrency("GBP");

        when(mockedBindingResult.hasErrors()).thenReturn(false);
        when(mockedIpResolvingService.resolve(UK_IP)).thenReturn(ipResult);
        when(mockedCryptoPriceService.getCryptoUnitValue(BITCOIN_CODE, ipResult.getCurrency())).thenThrow(new GeneralCoinbaseApiSideException());

        assertThat(viewController.showCryptoUnitPrice(cryptocurrencyPriceRequest, mockedBindingResult, mockedModel, mockedHttpServletRequest)).isEqualTo("index");
    }

    @Test
    public void whenCalledCryptoUnitPriceAndAllServicesWorksFine_thenCorrect() {
        CryptocurrencyPriceRequest cryptocurrencyPriceRequest = new CryptocurrencyPriceRequest(BITCOIN_CODE, UK_IP);
        IpResult ipResult = new IpResult()
                .setCountryCode("GB")
                .setCurrency("GBP");
        Money unitValue = new Money()
                .setAmount(new BigDecimal("35495.26"))
                .setCurrency("GBP");

        when(mockedBindingResult.hasErrors()).thenReturn(false);
        when(mockedIpResolvingService.resolve(UK_IP)).thenReturn(ipResult);
        when(mockedCryptoPriceService.getCryptoUnitValue(BITCOIN_CODE, ipResult.getCurrency())).thenReturn(unitValue);

        assertThat(viewController.showCryptoUnitPrice(cryptocurrencyPriceRequest, mockedBindingResult, mockedModel, mockedHttpServletRequest)).isEqualTo("index");
    }
}
