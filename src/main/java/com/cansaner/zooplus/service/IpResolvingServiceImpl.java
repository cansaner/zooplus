package com.cansaner.zooplus.service;

import com.cansaner.zooplus.service.model.IPResult;
import com.cansaner.zooplus.service.proxy.IpApiService;
import com.cansaner.zooplus.service.proxy.exception.IpApiSideException;
import com.cansaner.zooplus.service.proxy.model.IPGeolocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by cansaner on 04/04/22.
 */
@Service
public class IpResolvingServiceImpl implements IpResolvingService {
	private final IpApiService ipApiService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public IpResolvingServiceImpl(IpApiService ipApiService) {
		this.ipApiService = ipApiService;
	}

	@Override
	public IPResult resolve(String ip) throws IpApiSideException {
		return getIPGeolocation(ip);
	}

	private IPResult getIPGeolocation(String ip) {
		IPGeolocation geoData = ipApiService.getIPGeolocation(ip);
		return new IPResult().setCountryCode(geoData.getCountryCode())
				.setCurrency(geoData.getCurrency());
	}
}
