package com.example.cryptokeeper.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cryptocurrency_id")
    private CryptoCurrency cryptoCurrency;

    private BigDecimal quantity;

    private LocalDateTime dateTime;
}
