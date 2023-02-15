package com.macgrouptech.macgrouptechblogapplication.repositories;

import com.macgrouptech.macgrouptechblogapplication.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findOneByEmail(String email);
}
