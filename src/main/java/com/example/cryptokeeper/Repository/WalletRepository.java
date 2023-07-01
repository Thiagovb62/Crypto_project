package com.example.cryptokeeper.Repository;

import com.example.cryptokeeper.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {


    @Query("SELECT w FROM Wallet w left join User u on u.id = :id ")
    Wallet findByUserId(Long id);
}
