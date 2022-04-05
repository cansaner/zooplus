package com.cansaner.zooplus.service;

import com.cansaner.zooplus.service.model.Money;
import com.cansaner.zooplus.service.proxy.exception.CoinbaseApiSideException;

/**
 * Created by cansaner on 05/04/22.
 */
public interface CryptoPriceService {
    Money getCryptoUnitValue(String cryptoCode, String currencyCode) throws CoinbaseApiSideException;
}
