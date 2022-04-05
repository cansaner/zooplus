package com.cansaner.zooplus.service;

import com.cansaner.zooplus.service.model.IpResult;
import com.cansaner.zooplus.service.proxy.exception.IpApiSideException;

/**
 * Created by cansaner on 04/04/22.
 */
public interface IpResolvingService {
    IpResult resolve(String ip) throws IpApiSideException;
}
