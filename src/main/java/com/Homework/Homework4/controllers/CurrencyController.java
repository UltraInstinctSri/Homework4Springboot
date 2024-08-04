package com.Homework.Homework4.controllers;

import com.Homework.Homework4.clients.CurrencyClient;
import com.Homework.Homework4.dto.CurrencyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/currency")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyClient currencyClient;

    @GetMapping("/convertCurrency")
    public CurrencyDTO getConvertedCurrency
            ( @RequestParam String fromCurrency,
              @RequestParam String toCurrency,
              @RequestParam double units)
    {
       return currencyClient.ConvertedCurrency(fromCurrency , toCurrency , units);
    }
}
