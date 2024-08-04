package com.Homework.Homework4.clients;

import com.Homework.Homework4.dto.CurrencyDTO;

public interface CurrencyClient {
    CurrencyDTO ConvertedCurrency (String fromCurrency , String toCurrency , double units);
}
