package com.cansaner.zooplus.service;

import com.cansaner.zooplus.service.model.IpResult;
import com.cansaner.zooplus.service.proxy.IpApiService;
import com.cansaner.zooplus.service.proxy.exception.IpApiSideException;
import com.cansaner.zooplus.service.proxy.exception.StatusFailedIpApiSideException;
import com.cansaner.zooplus.service.proxy.model.IPGeolocation;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by cansaner on 05/04/22.
 */
public class IpResolvingServiceUnitTest {

    private static IpResolvingServiceImpl ipResolvingService;
    private static IpApiService mockedIpApiService;

    @BeforeClass
    public static void setUpIpResolvingServiceInstance() {
        mockedIpApiService = mock(IpApiService.class);
        ipResolvingService = new IpResolvingServiceImpl(mockedIpApiService);
    }

    @Test
    public void whenCalledResolve_thenCorrect() {
        String germanIp = "104.108.78.0";
        IPGeolocation geoData = new IPGeolocation()
                .setStatus("success")
                .setCountryCode("DE")
                .setCurrency("EUR")
                .setQuery(germanIp);

        IpResult expectedResult = new IpResult()
                .setCountryCode("DE")
                .setCurrency("EUR");

        when(mockedIpApiService.getIPGeolocation(germanIp)).thenReturn(geoData);

        IpResult actualResult = ipResolvingService.resolve(germanIp);
        assertThat(actualResult.getCountryCode()).isEqualTo(expectedResult.getCountryCode());
        assertThat(actualResult.getCurrency()).isEqualTo(expectedResult.getCurrency());
    }

    @Test(expected = IpApiSideException.class)
    public void whenCalledResolveAndIpApiFailed_thenCorrect() {
        String reservedIp = "0.0.0.0";
        IPGeolocation geoData = new IPGeolocation()
                .setStatus("fail")
                .setMessage("reserved range")
                .setQuery(reservedIp);

        IpResult expectedResult = new IpResult()
                .setCountryCode("DE")
                .setCurrency("EUR");

        when(mockedIpApiService.getIPGeolocation(reservedIp)).thenThrow(new StatusFailedIpApiSideException(geoData.getMessage()));

        ipResolvingService.resolve(reservedIp);
    }
}
