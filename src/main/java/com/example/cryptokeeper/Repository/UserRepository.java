package com.example.cryptokeeper.Repository;

import com.example.cryptokeeper.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN FETCH Wallet w on u.wallet.id = w.id ")
    List<User> findAllWithWallet();

    @Query("SELECT u FROM User u JOIN FETCH Wallet w on u.wallet.id = w.id WHERE u.id = :id")
    User findUserAndWalletByUserId(long id);
}
