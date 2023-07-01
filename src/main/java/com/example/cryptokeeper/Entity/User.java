package com.example.cryptokeeper.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "users")
@Data
public class User  {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_wallet_id")
    private Wallet wallet;
}
