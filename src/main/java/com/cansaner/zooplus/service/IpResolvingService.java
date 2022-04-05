package com.cansaner.zooplus.service;

import com.cansaner.zooplus.service.model.IPResult;
import com.cansaner.zooplus.service.proxy.exception.IpApiSideException;

/**
 * Created by cansaner on 04/04/22.
 */
public interface IpResolvingService {
  IPResult resolve(String ip) throws IpApiSideException;
}
