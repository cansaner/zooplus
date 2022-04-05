package com.baeldung.crud.service.proxy;

import com.baeldung.crud.service.proxy.model.IPGeolocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cansaner on 04/04/22.
 */
@Service
public class IpApiService {

  @Value( "${ipapi.baseUrl}" )
  private String ipApiBaseUrl;

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;
  private Logger logger = LoggerFactory.getLogger( getClass() );

  @Autowired
  public IpApiService(ObjectMapper objectMapper,
                      ApiRestTemplateBuilder apiRestTemplateBuilder) {
    this.objectMapper = objectMapper;
    this.restTemplate = apiRestTemplateBuilder.build( objectMapper);
  }

  public IPGeolocation getIPGeolocation(String ip) {
    final String url = ipApiBaseUrl + "/{ip}?fields={fields}";

    Map<String, String> params = new HashMap<String, String>();
    params.put("ip", ip);
    params.put("fields", "status,message,countryCode,currency,query");

    try {
      ResponseEntity<IPGeolocation> response = restTemplate.
          getForEntity( url, IPGeolocation.class, params);
      IPGeolocation result = response.getBody();
      if(result.getStatus().equalsIgnoreCase("fail")){
        logger.error( "Failed on the ip-api.com: ", result.getMessage() );
        throw new RuntimeException(result.getMessage());
      }

      return result;
//    } catch ( HttpClientErrorException e ) {
//      throw onClientException( e );
    } catch ( Exception e ) {
      logger.error( "Error while querying ip-api.com: ", e );
      throw e;
    }
  }

//  public SendOneSmsResponse sendSms( SendOneSmsRequest request ) {
//    final SmstoServiceConfiguration configuration = configurationService.get();
//    final String url = prepareSmstoBaseUrl( configuration ) + "/message/send";
//
//    final HttpEntity<SendOneSmsRequest> entity = new HttpEntity<>( request );
//    try {
//      ResponseEntity<SendOneSmsResponse> response = restTemplate.
//          exchange( url, HttpMethod.POST, entity, SendOneSmsResponse.class );
//      return response.getBody();
//    } catch ( HttpClientErrorException e ) {
//      throw onClientException( e );
//    } catch ( Exception e ) {
//      logger.error( "Error while querying Smsto: ", e );
//      throw e;
//    }
//  }

//  private String prepareSmstoBaseUrl( SmstoServiceConfiguration configuration ) {
//    return configuration.getBaseUrl();
//  }
//
//  private RuntimeException onClientException( HttpClientErrorException e ) {
//    RuntimeException runtimeException = handleSmstoException( e );
//    if ( runtimeException instanceof SmstoException) {
//      return SmstoServiceUtils.handleSmstoException( (SmstoException) runtimeException );
//    } else {
//      return runtimeException;
//    }
//  }
//
//  private RuntimeException handleSmstoException( final HttpClientErrorException e ) {
//    final String errorBody = e.getResponseBodyAsString();
//    logger.debug( "Error from Smsto: " + errorBody );
//    if ( errorBody != null && errorBody.startsWith( "{" ) ) {
//      try {
//        SmstoError error = objectMapper.readValue( errorBody, SmstoError.class );
//        return new SmstoException( error.getErrors() );
//      } catch ( IOException ioe ) {
//        logger.debug( "Exception while deserializing smsto error: " + ioe.getMessage() );
//        return new GeneralSmstoSideException();
//      }
//    }
//    return e;
//  }
}
