package com.cansaner.zooplus.config;

import com.cansaner.zooplus.service.IpResolvingService;
import com.cansaner.zooplus.service.model.IpResult;
import com.cansaner.zooplus.tools.HttpUtils;
import com.cansaner.zooplus.tools.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by cansaner on 04/04/22.
 */
@Configuration
public class IPLocaleResolver extends SessionLocaleResolver {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final IpResolvingService ipResolvingService;

    public IPLocaleResolver(IpResolvingService ipResolvingService) {
        this.ipResolvingService = ipResolvingService;
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        if (request.getMethod().equals(HttpMethod.POST.name())) {
            String ip = request.getParameter("ipAddress");
            logger.info("Setting locale from IP: {}", ip);
            if (ip == null || ip.trim().isEmpty()) {
                ip = HttpUtils.getRequestIP(request);
                logger.info("Request IP field is empty so getting IP from request: {}", ip);
            }
            try {
                IpResult result = ipResolvingService.resolve(ip);
                return LocaleUtils.resolveMessage(result.getCountryCode());
            } catch (Exception e) {
                logger.error("Locale is set to default value: ", e);
                return Locale.ENGLISH;
            }
        } else {
            return request.getLocale();
        }
    }
}
