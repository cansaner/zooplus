package com.cansaner.zooplus.service.proxy;

import com.cansaner.zooplus.service.proxy.exception.CoinbaseApiSideException;
import com.cansaner.zooplus.service.proxy.exception.GeneralCoinbaseApiSideException;
import com.cansaner.zooplus.service.proxy.model.CryptoSpotValue;
import com.cansaner.zooplus.service.proxy.model.CryptoSpotValueData;
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
 * Created by cansaner on 05/04/22.
 */
@Service
public class CoinbaseApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Value("${coinbaseapi.baseUrl}")
    private String ipApiBaseUrl;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public CoinbaseApiService(ObjectMapper objectMapper,
                              ApiRestTemplateBuilder apiRestTemplateBuilder) {
        this.objectMapper = objectMapper;
        this.restTemplate = apiRestTemplateBuilder.build(objectMapper);
    }

    public CryptoSpotValue getSpotValue(String baseCrypto, String currency) throws CoinbaseApiSideException {
        final String url = ipApiBaseUrl + "/prices/{currency_pair}/spot";

        Map<String, String> params = new HashMap<String, String>();
        params.put("currency_pair", baseCrypto + "-" + currency);

        try {
            ResponseEntity<CryptoSpotValueData> response = restTemplate.
                    getForEntity(url, CryptoSpotValueData.class, params);
            return response.getBody().getData();
        } catch (HttpClientErrorException e) {
            GeneralCoinbaseApiSideException ge = new GeneralCoinbaseApiSideException();
            logger.error(ge.getMessage() + ": ", e);
            throw ge;
        } catch (Exception e) {
            logger.error("Error while querying api.coinbase.com: ", e);
            throw e;
        }
    }
}
