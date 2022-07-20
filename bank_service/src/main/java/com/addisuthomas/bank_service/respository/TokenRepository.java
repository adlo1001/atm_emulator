package com.addisuthomas.bank_service.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.addisuthomas.bank_service.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

}
