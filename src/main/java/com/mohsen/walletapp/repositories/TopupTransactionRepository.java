package com.mohsen.walletapp.repositories;

import com.mohsen.walletapp.models.TopupTxnHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TopupTransactionRepository extends JpaRepository<TopupTxnHistory, String> {

}
