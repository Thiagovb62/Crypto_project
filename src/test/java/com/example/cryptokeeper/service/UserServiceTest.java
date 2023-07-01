package com.example.cryptokeeper.service;

import com.example.cryptokeeper.Entity.User;
import com.example.cryptokeeper.Entity.Wallet;
import com.example.cryptokeeper.Repository.CryptoCurrencyRepository;
import com.example.cryptokeeper.Repository.UserRepository;
import com.example.cryptokeeper.Repository.WalletRepository;
import com.example.cryptokeeper.Service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private WalletRepository walletRepository;

    @Mock
    private CryptoCurrencyRepository cryptoCurrencyRepository;
    @Test
    void testCreateWallet() {
        // Mocking repository method
        when(walletRepository.save(Mockito.any(Wallet.class))).thenAnswer(invocation -> invocation.getArgument(0));
        // Call the method
        Wallet result = userService.createWallet();
        // Assertions
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(1000.0), result.getBalance());
        assertNull(result.getTransactions());
        assertEquals(BigDecimal.valueOf(0.0), result.getQtdBitcoin());
        assertEquals(BigDecimal.valueOf(0.0), result.getQtdEthereum());
    }
    @Test
    void testSave() {
        // Mocking data
        User user = new User();
        user.setId(1L);
        user.setUsername("john");
        user.setPassword("password");
        user.setEmail("john@example.com");

        Wallet wallet = new Wallet();
        wallet.setId(1L);

        // Mocking repository methods
        when(walletRepository.save(Mockito.any(Wallet.class))).thenReturn(wallet);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        // Call the method
        User result = userService.save(user);

        // Assertions
        assertNotNull(result);
        assertEquals(user, result);
        assertEquals(wallet, result.getWallet());
    }
}
