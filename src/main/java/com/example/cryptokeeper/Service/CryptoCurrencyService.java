package com.example.cryptokeeper.Service;

import com.example.cryptokeeper.Repository.CryptoCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cryptokeeper.Entity.CryptoCurrency;

import java.util.List;


@Service
public class CryptoCurrencyService {

    @Autowired
    private CryptoCurrencyRepository currencyRepository;
     public CryptoCurrency save(CryptoCurrency currency) {
          return currencyRepository.save(currency);
     }
     public List<CryptoCurrency> findAll() {
          return currencyRepository.findAll();
     }

     public void deleteById(Long id) {
          currencyRepository.deleteById(id);
     }
}
