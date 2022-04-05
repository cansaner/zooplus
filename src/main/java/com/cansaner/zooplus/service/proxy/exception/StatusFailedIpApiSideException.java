package com.cansaner.zooplus.service.proxy.exception;

/**
 * Created by cansaner on 04/04/22.
 */
public class GeneralIpApiSideException extends IpApiSideException {

	private static final long serialVersionUID = -3452795207171940875L;

	public GeneralIpApiSideException() {
		super( "Some errors occurred on ip-api.com" );
	}
}
