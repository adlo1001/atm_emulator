package com.addisuthomas.bank_service.serviceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addisuthomas.atm_lib.misc.AuthChoice;
import com.addisuthomas.atm_lib.model.CardModel;
import com.addisuthomas.bank_service.entity.Card;
import com.addisuthomas.bank_service.respository.CardRepository;
import com.addisuthomas.bank_service.service.CardService;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository cardRepository;

	@Override
	public CardModel validateCardNumber(String cardNumber) {
		Card card = cardRepository.findByCardNumber(cardNumber);
		if (Objects.isNull(card)) {
			throw new RuntimeException("Invalid Card Number!");
		}
		// TODO add MapStruct
		return new CardModel(card.getCardNumber(), card.getCardStatus(), card.getLoginTries(), card.getAuthChoice(),
				null);
	}

	@Override
	public CardModel chooseAuthMethod(String cardNo, String authPreferred) {
		Card card = cardRepository.findByCardNumber(cardNo);
		if (Objects.isNull(card)) {
			throw new RuntimeException(cardNo + " Invalid Card Number!");
		}
		if (Integer.parseInt(authPreferred) == 2)
			card.setAuthChoice(AuthChoice.FINGERPRINT);
		else if (Integer.parseInt(authPreferred) == 1)
			card.setAuthChoice(AuthChoice.PIN);
		else
			;
		cardRepository.save(card);
		return new CardModel(card.getCardNumber(), card.getCardStatus(), card.getLoginTries(), card.getAuthChoice(),
				null);

	}

}
