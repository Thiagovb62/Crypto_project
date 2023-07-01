package com.example.cryptokeeper.Service;

import com.example.cryptokeeper.Entity.User;
import com.example.cryptokeeper.Entity.Wallet;
import com.example.cryptokeeper.Repository.UserRepository;
import com.example.cryptokeeper.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;
    public List<User> findAll() {
        return userRepository.findAllWithWallet();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Wallet createWallet() {
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(1000.0));
        wallet.setTransactions(null);
        wallet.setQtdBitcoin(BigDecimal.valueOf(0.0));
        wallet.setQtdEthereum(BigDecimal.valueOf(0.0));
        return walletRepository.save(wallet);
    }

    public User save(User user) {
         Wallet wallet = createWallet();
         user.setWallet(wallet);
        return userRepository.save(user);
    }
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
