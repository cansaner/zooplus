package com.cansaner.zooplus.service.proxy;

import com.cansaner.zooplus.service.proxy.exception.GeneralIpApiSideException;
import com.cansaner.zooplus.service.proxy.exception.IpApiSideException;
import com.cansaner.zooplus.service.proxy.exception.StatusFailedIpApiSideException;
import com.cansaner.zooplus.service.proxy.model.IPGeolocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cansaner on 04/04/22.
 */
@Service
public class IpApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${ipapi.baseUrl}")
    private String ipApiBaseUrl;

    @Autowired
    public IpApiService(ObjectMapper objectMapper,
                        ApiRestTemplateBuilder apiRestTemplateBuilder) {
        this.objectMapper = objectMapper;
        this.restTemplate = apiRestTemplateBuilder.build(objectMapper);
    }

    public IPGeolocation getIPGeolocation(String ip) throws IpApiSideException {
        final String url = ipApiBaseUrl + "/{ip}?fields={fields}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("ip", ip);
        params.put("fields", "status,message,countryCode,currency,query");

        try {
            ResponseEntity<IPGeolocation> response = restTemplate.
                    getForEntity(url, IPGeolocation.class, params);
            IPGeolocation result = response.getBody();
            if (result.getStatus().equalsIgnoreCase("fail")) {
                logger.error("Failed on the ip-api.com: ", result.getMessage());
                throw new StatusFailedIpApiSideException(result.getMessage());
            }
            return result;
        } catch (HttpClientErrorException e) {
            GeneralIpApiSideException ge = new GeneralIpApiSideException();
            logger.error(ge.getMessage() + ": ", e);
            throw ge;
        } catch (Exception e) {
            logger.error("Error while querying ip-api.com: ", e);
            throw e;
        }
    }
}
