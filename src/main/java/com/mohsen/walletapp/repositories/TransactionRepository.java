package com.mohsen.walletapp.repositories;

import com.mohsen.walletapp.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TransactionRepository extends JpaRepository<Transactions, String> {

    //boolean existsByReferenceOrId(String tran_ref, String cart_id);

    //boolean existsByTranRefOrId(String tran_ref, String cart_id);

    boolean existsByTranRef(String tran_ref);

   /* @Query(value = "select case when count(distinct tran_ref  ) = 1 then 'true' else 'false' end as bool from transactions where tran_ref= ?1", nativeQuery = true)
    boolean existTranV2(String tran_ref);*/
}
