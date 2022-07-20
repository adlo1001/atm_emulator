package com.addisuthomas.bank_service.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addisuthomas.bank_service.entity.TransactionHistory;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

}
