package com.example.webapp.serverapp.repositories;

import com.example.webapp.serverapp.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmailAndPassword(String email, String password);
    Account findByEmail(String email);
}
