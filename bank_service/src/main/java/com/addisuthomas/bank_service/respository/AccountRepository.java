package com.addisuthomas.bank_service.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addisuthomas.bank_service.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
