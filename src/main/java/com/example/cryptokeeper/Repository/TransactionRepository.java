package com.example.cryptokeeper.Repository;

import com.example.cryptokeeper.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
