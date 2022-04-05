package com.cansaner.zooplus.service.proxy.exception;

/**
 * Created by cansaner on 04/04/22.
 */
public class GeneralCoinbaseApiSideException extends IpApiSideException {

	private static final long serialVersionUID = -1452795207171940875L;

	public GeneralCoinbaseApiSideException() {
		super( "Some errors occurred on api.coinbase.com" );
	}
}
