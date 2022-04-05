package com.baeldung.crud;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

//@RestClientTest(IpApiService.class)
class ipApiServiceTest {

//  @Autowired
//  private IpApiService ipApiService;
//
//  @Autowired
//  private ObjectMapper objectMapper;
//
//  @Autowired
//  private ApiRestTemplateBuilder apiRestTemplateBuilder;
//
//  @Autowired
//  private MockRestServiceServer mockRestServiceServer;
//
//  @Test
//  void ipApiServiceSuccessfullyReturnsGeoData() {
//    String germanIp = "104.108.78.0";
//    String json = "{\"status\":\"success\",\"countryCode\":\"DE\",\"currency\":\"EUR\",\"query\":\"104.108.78.0\"}";
//
//    this.mockRestServiceServer
//      .expect(requestTo("http://ip-api.com/json/104.108.78.0?fields=status,message,countryCode,currency,query"))
//      .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
//
//    IPGeolocation geoData = ipApiService.getIPGeolocation(germanIp);
//
//    assertNotNull(geoData);
//    assertEquals("success", geoData.getStatus());
//    assertEquals("DE", geoData.getCountryCode());
//    assertEquals("EUR", geoData.getCurrency());
//    assertEquals(germanIp, geoData.getCurrency());
//    assertNull(geoData.getMessage());
//  }
//
//  @Test
//  void ipApiServiceThrowsExceptionWhenStatusFailed() throws Exception {
//    String reservedIp = "0.0.0.0";
//
//    String json = this.objectMapper
//      .writeValueAsString(new IPGeolocation()
//              .setStatus("fail")
//              .setMessage("reserved range")
//              .setQuery(reservedIp));
//
//    this.mockRestServiceServer
//      .expect(requestTo("http://ip-api.com/json/0.0.0.0?fields=status,message,countryCode,currency,query"))
//      .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
//
//    assertThrows(StatusFailedIpApiSideException.class, () -> ipApiService.getIPGeolocation(reservedIp));
//  }
//
//  @Test
//  void userClientThrowsExceptionWhenNoUserIsFound() {
//    String germanIp = "104.108.78.0";
//
//    this.mockRestServiceServer.expect(requestTo("http://ip-api.com/json/104.108.78.0?fields=status,message,countryCode,currency,query"))
//      .andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));
//
//    assertThrows(GeneralIpApiSideException.class, () -> ipApiService.getIPGeolocation(germanIp));
//  }
}
