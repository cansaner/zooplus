package com.cansaner.zooplus.service;

import com.cansaner.zooplus.service.model.Money;
import com.cansaner.zooplus.service.proxy.CoinbaseApiService;
import com.cansaner.zooplus.service.proxy.exception.CoinbaseApiSideException;
import com.cansaner.zooplus.service.proxy.model.CryptoSpotValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by cansaner on 05/04/22.
 */
@Service
public class CryptoPriceServiceImpl implements CryptoPriceService {
    private final CoinbaseApiService coinbaseApiService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public CryptoPriceServiceImpl(CoinbaseApiService coinbaseApiService) {
        this.coinbaseApiService = coinbaseApiService;
    }

    @Override
    public Money getCryptoUnitValue(String cryptoCode, String currencyCode) throws CoinbaseApiSideException {
        return getSpotValue(cryptoCode, currencyCode);
    }

    private Money getSpotValue(String baseCrypto, String currency) {
        CryptoSpotValue value = coinbaseApiService.getSpotValue(baseCrypto, currency);
        return new Money().setCurrency(value.getCurrency())
                .setAmount(new BigDecimal(value.getAmount()));
    }
}
