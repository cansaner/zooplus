package com.cansaner.zooplus.service.proxy.exception;

/**
 * Created by cansaner on 05/04/22.
 */
public abstract class CoinbaseApiSideException extends RuntimeException {

    public CoinbaseApiSideException() {}

    public CoinbaseApiSideException(String message) {
        super(message);
    }
}
