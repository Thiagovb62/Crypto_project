package com.example.cryptokeeper.Controller;

import com.example.cryptokeeper.Entity.CryptoCurrency;
import com.example.cryptokeeper.Service.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cryptocurrency")
public class CryptoCurrencyController {

    @Autowired
    private CryptoCurrencyService currencyService;

    @PostMapping("/save")
    public CryptoCurrency save(@RequestBody CryptoCurrency currency) {
        return currencyService.save(currency);
    }

    @GetMapping("/findAll")
    public Iterable<CryptoCurrency> findAll() {
        return currencyService.findAll();
    }

    @DeleteMapping("/deleteById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        currencyService.deleteById(id);
    }

}
