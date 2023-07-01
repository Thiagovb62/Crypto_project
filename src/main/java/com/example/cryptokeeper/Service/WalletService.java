package com.example.cryptokeeper.Service;

import com.example.cryptokeeper.Entity.User;
import com.example.cryptokeeper.Entity.Wallet;
import com.example.cryptokeeper.Repository.UserRepository;
import com.example.cryptokeeper.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserService userService;

    public Wallet findByUserId(Long id) {
        return walletRepository.findByUserId(id);
    }

    public List<Wallet> findAllWallets() {
        return walletRepository.findAll();
    }
    public Wallet findById(Long id) {
        return walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
    }
    public void deleteById(Long id) {
        walletRepository.deleteById(id);
    }
}
