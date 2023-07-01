package com.example.cryptokeeper.Controller;

import com.example.cryptokeeper.Entity.Wallet;
import com.example.cryptokeeper.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/find")
    public Iterable<Wallet> findAll() {
        return walletService.findAllWallets();
    }

    @GetMapping("/find/user/{id}")
    public Wallet findByUserId(@PathVariable  Long id) {
        return walletService.findByUserId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        walletService.deleteById(id);
    }
}
