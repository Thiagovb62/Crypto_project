package com.example.cryptokeeper.Service;

import com.example.cryptokeeper.Entity.CryptoCurrency;
import com.example.cryptokeeper.Entity.Transaction;
import com.example.cryptokeeper.Entity.User;
import com.example.cryptokeeper.Entity.Wallet;
import com.example.cryptokeeper.Repository.CryptoCurrencyRepository;
import com.example.cryptokeeper.Repository.TransactionRepository;
import com.example.cryptokeeper.Repository.UserRepository;
import com.example.cryptokeeper.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TrasactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction buyCurrency(Transaction transaction, Long userId) {
        User user = userService.findById(userId);
        Wallet wallet = walletRepository.findById(user.getWallet().getId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (wallet == null) {
            throw new RuntimeException("User does not have a wallet");
        }

        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findById(Optional.of(transaction.getCryptoCurrency().getId())
                .orElseThrow(() -> new RuntimeException("CryptoCurrency not found"))).orElseThrow(() -> new RuntimeException("CryptoCurrency not found"));

        BigDecimal totalAmount = transaction.getQuantity().multiply(cryptoCurrency.getCurrentValue());
        BigDecimal currentBalance = wallet.getBalance();

        if (currentBalance.compareTo(totalAmount) < 0) {
            throw new RuntimeException("Insufficient balance in the wallet");
        }

        if (cryptoCurrency.getName().equals("Bitcoin")) {
            wallet.setQtdBitcoin((transaction.getQuantity()));
        } else if (cryptoCurrency.getName().equals("Ethereum")) {
            wallet.setQtdEthereum(transaction.getQuantity());
        } else {
            throw new RuntimeException("CryptoCurrency not found");
        }

        wallet.getTransactions().add(transaction);
        wallet.setBalance(currentBalance.subtract(totalAmount));
        transaction.setCryptoCurrency(cryptoCurrency);

        walletRepository.save(wallet);
        return transactionRepository.save(transaction);
    }

    public Transaction sellCurrency(Transaction transaction, Long userId) {
        User user = userService.findById(userId);
        Wallet wallet = walletRepository.findById(user.getWallet().getId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findById(Optional.of(transaction.getCryptoCurrency().getId())
                .orElseThrow(() -> new RuntimeException("CryptoCurrency not found"))).orElseThrow(() -> new RuntimeException("CryptoCurrency not found"));

        BigDecimal totalAmount = transaction.getQuantity().multiply(cryptoCurrency.getCurrentValue());
        BigDecimal currentBalance = wallet.getBalance();

        if (cryptoCurrency.getName().equals("Bitcoin")) {
            if (wallet.getQtdBitcoin().compareTo(transaction.getQuantity()) < 0) {
                throw new RuntimeException("qtd bitcoin insuficiente");
            }
            wallet.setQtdBitcoin((wallet.getQtdBitcoin().subtract(transaction.getQuantity())));
        } else if (cryptoCurrency.getName().equals("Ethereum")) {
            if (wallet.getQtdEthereum().compareTo(transaction.getQuantity()) < 0) {
                throw new RuntimeException("qtd ethereum insuficiente");
            }
            wallet.setQtdEthereum((wallet.getQtdEthereum().subtract(transaction.getQuantity())));
        } else {
            throw new RuntimeException("CryptoCurrency not found");
        }

        wallet.getTransactions().add(transaction);
        wallet.setBalance(currentBalance.add(totalAmount));
        transaction.setCryptoCurrency(cryptoCurrency);

        walletRepository.save(wallet);
        return transactionRepository.save(transaction);
    }


}
