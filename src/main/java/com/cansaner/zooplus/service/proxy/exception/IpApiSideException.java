package com.cansaner.zooplus.service.proxy.exception;

/**
 * Created by cansaner on 04/04/22.
 */
public abstract class IpApiSideException extends RuntimeException {

    public IpApiSideException() {}

    public IpApiSideException(String message) {
        super(message);
    }
}
