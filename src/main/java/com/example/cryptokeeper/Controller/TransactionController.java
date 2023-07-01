package com.example.cryptokeeper.Controller;

import com.example.cryptokeeper.Entity.Transaction;
import com.example.cryptokeeper.Service.TrasactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TrasactionService transactionService;


    @PostMapping("/buy/{userId}")
    public Transaction buyCurrency(@RequestBody Transaction transaction, @PathVariable Long userId ) {
        return transactionService.buyCurrency(transaction, userId);
    }

    @PostMapping("/sell/{userId}")
    public Transaction sellCurrency(@RequestBody Transaction transaction, @PathVariable Long userId ) {
        return transactionService.sellCurrency(transaction, userId);
    }

}
