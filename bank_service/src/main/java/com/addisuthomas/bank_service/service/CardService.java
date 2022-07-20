package com.addisuthomas.bank_service.service;

import com.addisuthomas.atm_lib.model.CardModel;

public interface CardService {
	CardModel validateCardNumber(String cardNumber);

	CardModel chooseAuthMethod(String cardNumber, String authPreferred);
	
}
