package com.cansaner.zooplus.controller;

import com.cansaner.zooplus.dto.CryptocurrencyPriceRequest;
import com.cansaner.zooplus.service.CryptoPriceService;
import com.cansaner.zooplus.service.IpResolvingService;
import com.cansaner.zooplus.service.model.IpResult;
import com.cansaner.zooplus.service.model.Money;
import com.cansaner.zooplus.service.proxy.exception.CoinbaseApiSideException;
import com.cansaner.zooplus.service.proxy.exception.IpApiSideException;
import com.cansaner.zooplus.tools.HttpUtils;
import com.cansaner.zooplus.tools.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by cansaner on 04/04/22.
 */
@Controller
public class ViewController {
    private final IpResolvingService IpResolvingService;

    private final CryptoPriceService cryptoPriceService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ViewController(IpResolvingService IpResolvingService, CryptoPriceService cryptoPriceService) {
        this.IpResolvingService = IpResolvingService;
        this.cryptoPriceService = cryptoPriceService;
    }

    @GetMapping(value = "/")
    public String showRoot(Model model) {
        model.addAttribute("cryptocurrencypricerequest", new CryptocurrencyPriceRequest());
        model.addAttribute("priceText", null);
        return "index";
    }

    @GetMapping("/index")
    public String showIndex(Model model) {
        model.addAttribute("cryptocurrencypricerequest", new CryptocurrencyPriceRequest());
        model.addAttribute("priceText", null);
        return "index";
    }

    @PostMapping("/index")
    public String showCryptoUnitPrice(@Valid CryptocurrencyPriceRequest request, BindingResult result, Model model, HttpServletRequest servletRequest) {
        model.addAttribute("cryptocurrencypricerequest", request);
        if (result.hasErrors()) {
            return "index";
        }

        String ip = request.getIpAddress();
        logger.info("Setting locale from IP: {}", ip);
        if (ip == null || ip.trim().isEmpty()) {
            ip = HttpUtils.getRequestIP(servletRequest);
            logger.info("Request IP field is empty so getting IP from request: {}", ip);
        }
        try {
            IpResult ipResult = IpResolvingService.resolve(ip);
            Locale locale = LocaleUtils.resolveMessage(ipResult.getCountryCode());
            Money unitValue = cryptoPriceService.getCryptoUnitValue(request.getCryptoCode(), ipResult.getCurrency());

            model.addAttribute("priceText", LocaleUtils.resolveCurrencySymbol(ipResult.getCurrency())
                    + localizePrice(locale, unitValue.getAmount()));
        } catch (IpApiSideException e) {
            return "redirect:/index";
        } catch (CoinbaseApiSideException e) {
            logger.error("Error while querying api.coinbase.com: ", e);
            model.addAttribute("priceText", "UNKNOWN");
        }
        return "index";
    }

    private String localizePrice(final Locale locale, final BigDecimal amount) {
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        String localizedPrice = numberFormat.format(amount.doubleValue());
        return localizedPrice;
    }
}
