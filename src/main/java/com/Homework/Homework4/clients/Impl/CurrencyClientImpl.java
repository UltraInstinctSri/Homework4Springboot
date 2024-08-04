package com.Homework.Homework4.clients.Impl;

import com.Homework.Homework4.clients.CurrencyClient;
import com.Homework.Homework4.dto.CurrencyDTO;
import com.Homework.Homework4.entities.CurrencyEntity;
import com.Homework.Homework4.repositories.CurrencyRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyClientImpl implements CurrencyClient {
    private final RestClient restClient;

    @Value("${currency.api.key}")
    private String apiKey;

    private final ModelMapper modelMapper;
    private final CurrencyRepo currencyRepo;

    Logger log = LoggerFactory.getLogger(CurrencyClientImpl.class);

    @Override
    public CurrencyDTO ConvertedCurrency(String fromCurrency, String toCurrency, double units) {
      log.trace("Trying to convert currency");
      try{
          log.info("Inside try block of currency convertor");
          //converted the url in correct format using api key
          String url = String.format("?apikey=%s&base_currency=%s&currencies=%s", apiKey, fromCurrency, toCurrency);

          //getting the current rate of toCurrency from fromCurrency
          Map<String, Map<String, Double>> response =
                  restClient.get()
                  .uri(url)
                  .retrieve()
                          .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                              log.error(new String(res.getBody().readAllBytes()));
                              throw new RuntimeException("could not convert the employee");
                          })
                          .body(new ParameterizedTypeReference<>() {
                          });
          log.debug("Successfully retrieved the employees in getAllEmployees");

          //getting current conversion rate than turning units into toCurrency
          if(response != null && response.containsKey("data")){
              Double conversionRate =  response.get("data").get(toCurrency);

              if(conversionRate !=null){
                  double amount  = units * conversionRate;
                  CurrencyEntity currencyEntity = new CurrencyEntity();
                  currencyEntity.setToCurrency(toCurrency);
                  currencyEntity.setFromCurrency(fromCurrency);
                  currencyEntity.setUnits(units);
                  currencyEntity.setConvertedCurrency(amount);
                  currencyRepo.save(currencyEntity);
                  return modelMapper.map(currencyEntity, CurrencyDTO.class);
              }
          }
          throw new RuntimeException("Failed to get conversion rate.");

      }

      catch(Exception e){
          log.error("Error occured while converting currency");
          throw new RuntimeException(e);
        }
    }
}
