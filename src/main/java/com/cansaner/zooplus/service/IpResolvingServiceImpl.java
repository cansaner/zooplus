package com.baeldung.crud.service;

import com.baeldung.crud.service.model.IPResult;
import com.baeldung.crud.service.proxy.IpApiService;
import com.baeldung.crud.service.proxy.model.IPGeolocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by cansaner on 13/08/19.
 */
@Service
public class IPResolvingServiceImpl implements IPResolvingService {
	private final IpApiService iPAPIService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public IPResolvingServiceImpl(IpApiService iPAPIService) {
		this.iPAPIService = iPAPIService;
	}

	@Override
	public IPResult resolve(String ip) {
		return getIPGeolocation(ip);
	}

//	@Override
//	public SendOneSmsResponse sendMessage( String userId, SMS sms) {
//		final User user = userRepositoryClient.getUser( userId );
//
//		if ( !Objects.equals( user.getMsisdn(), sms.getTo() ) ) {
//			throw new MsisdnMismatchException();
//		}
//
//		// TODO: getting balance of account is somewhat failing, we have to add this check after
////		if ( getBalance().getValue() < configurationService.get().getBalanceThreshold() ) {
////			throw new InsufficientFundsException();
////		}
//
//		logger.debug( "SMS CANSANER Sent: {}", sms.getMessage() );
//		return new SendOneSmsResponse().setSuccess(true);
////		return sendSms( SmstoServiceUtils.toSendOneSmsRequest( sms ) );
//	}
//
//	private SendOneSmsResponse sendSms( SendOneSmsRequest request ) {
//		try {
//			return smstoAPIService.sendSms( request );
//		} catch ( SmstoSideException smstoSideException ) {
//			throw onSmstoException( smstoSideException );
//		}
//	}
//
	private IPResult getIPGeolocation(String ip) {
		IPGeolocation geoData = iPAPIService.getIPGeolocation(ip);
		return new IPResult().setCountryCode(geoData.getCountryCode())
				.setCurrency(geoData.getCurrency());
	}
//
//	private SmstoSideException onSmstoException( SmstoSideException smstoSideException ) {
//		return smstoSideException;
//	}
}
