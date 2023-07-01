package com.example.cryptokeeper.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.example.cryptokeeper.Entity.CryptoCurrency;
public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
}
