package com.cansaner.zooplus.config;

import com.cansaner.zooplus.service.IpResolvingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by cansaner on 04/04/22.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver(IpResolvingService ipResolvingService) {
        return new IPLocaleResolver(ipResolvingService);
    }
}
