package com.baeldung.crud.service.proxy;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

/**
 * Created by cansaner on 04/04/22.
 */
@Component
public class ApiRestTemplateBuilder {

  private Logger logger = LoggerFactory.getLogger( getClass() );

  public RestTemplate build( ObjectMapper objectMapper) {
    final ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory( buildHttpComponentsClientHttpRequestFactory() );
    final RestTemplate restTemplate = new RestTemplate( factory );

    objectMapper.configure( SerializationFeature.FAIL_ON_EMPTY_BEANS, false );
    objectMapper.setSerializationInclusion( Include.NON_NULL );

    restTemplate.getMessageConverters().add( 0, new MappingJackson2HttpMessageConverter( objectMapper ) );
    restTemplate.getInterceptors().add( loggingInterceptor() );
    return restTemplate;
  }

  private HttpComponentsClientHttpRequestFactory buildHttpComponentsClientHttpRequestFactory() {
    final CloseableHttpClient httpClient = HttpClients.custom()
        .setSSLSocketFactory( new SSLConnectionSocketFactory( createSslContext(), new NoopHostnameVerifier() ) )
        .build();
    final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    clientHttpRequestFactory.setHttpClient( httpClient );
    return clientHttpRequestFactory;
  }

  private SSLContext createSslContext() {
    try {
      return org.apache.http.ssl.SSLContexts.custom()
          .loadTrustMaterial( null, ( x509Certificates, s ) -> true )
          .build();
    } catch ( NoSuchAlgorithmException | KeyManagementException | KeyStoreException e ) {
      throw new RuntimeException( "Failed to create ssl context" );
    }
  }

  private ClientHttpRequestInterceptor loggingInterceptor() {
    return ( request, body, execution ) -> {
      logger.info( "REST call method: {}, request URI: {}, headers: {}, request body: {}",
          request.getMethod(),
          request.getURI(),
          request.getHeaders().toSingleValueMap(),
          new String( body, Charset.forName( "UTF-8" ) ) );
      long startTime = System.currentTimeMillis();
      ClientHttpResponse response = execution.execute( request, body );
      long endTime = System.currentTimeMillis();
      logger.info( "REST response ({} ms) body: {}, headers: {}", ( endTime - startTime ),
          new BufferedReader( new InputStreamReader( response.getBody() ) ).lines().collect( Collectors
              .joining( "\n" ) ),
          response.getHeaders() );
      return response;
    };
  }
}