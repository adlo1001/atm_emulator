package com.addisuthomas.bank_service.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addisuthomas.bank_service.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

	Card findByCardNumber(String cardNumber);

}
