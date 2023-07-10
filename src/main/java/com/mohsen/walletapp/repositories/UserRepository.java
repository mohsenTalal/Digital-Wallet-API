package com.mohsen.walletapp.repositories;

import com.mohsen.walletapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

//    Optional<User> findByEmail(String username);
   //@Query( "select It from User It where It.email in ?1" )
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}

