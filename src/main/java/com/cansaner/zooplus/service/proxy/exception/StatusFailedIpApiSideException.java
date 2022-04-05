package com.cansaner.zooplus.service.proxy.exception;

/**
 * Created by cansaner on 04/04/22.
 */
public class StatusFailedIpApiSideException extends IpApiSideException {

    private static final long serialVersionUID = -2782795207171940875L;

    public StatusFailedIpApiSideException() {
        super("Status is failed on ip-api.com");
    }

    public StatusFailedIpApiSideException(String message) {
        super(message);
    }
}
