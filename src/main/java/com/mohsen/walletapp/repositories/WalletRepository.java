package com.mohsen.walletapp.repositories;

import com.mohsen.walletapp.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Wallet findByEmail(String email);
}
