package com.cansaner.zooplus.controller;

import javax.validation.Valid;

import com.cansaner.zooplus.dto.CryptocurrencyPriceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ViewController() {
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
    public String showCryptoUnitPrice(@Valid CryptocurrencyPriceRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("cryptocurrencypricerequest", request);
            return "index";
        }

        model.addAttribute("cryptocurrencypricerequest", request);
        model.addAttribute("priceText", "€6.99");

        return "index";
    }
}
