package com.mohsen.walletapp.repositories;

import com.mohsen.walletapp.models.CustomerDetails;
import com.mohsen.walletapp.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, String> {

}
