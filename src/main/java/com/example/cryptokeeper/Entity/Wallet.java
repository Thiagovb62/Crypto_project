package com.example.cryptokeeper.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "wallets")
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long id;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "trasaction_id")
    private List<Transaction> transactions;

    private BigDecimal balance;

    private BigDecimal qtdBitcoin;

    private BigDecimal qtdEthereum;
}