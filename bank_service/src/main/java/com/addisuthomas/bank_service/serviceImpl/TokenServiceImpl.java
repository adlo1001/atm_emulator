package com.addisuthomas.bank_service.serviceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.addisuthomas.atm_lib.misc.CardStatus;
import com.addisuthomas.atm_lib.misc.TransactionStatus;
import com.addisuthomas.atm_lib.model.CardModel;
import com.addisuthomas.atm_lib.model.ResultModel;
import com.addisuthomas.atm_lib.model.TokenModel;
import com.addisuthomas.bank_service.entity.Account;
import com.addisuthomas.bank_service.entity.Card;
import com.addisuthomas.bank_service.entity.Token;
import com.addisuthomas.bank_service.respository.AccountRepository;
import com.addisuthomas.bank_service.respository.CardRepository;
import com.addisuthomas.bank_service.respository.TokenRepository;
import com.addisuthomas.bank_service.service.TokenService;

public class TokenServiceImpl implements TokenService {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private TokenRepository tokenRepository;

	@Override
	public ResultModel storeToken(TokenModel token) {
		Card card = cardRepository.findByCardNumber(token.getCardNumber());
		if (Objects.isNull(card)) {
			return new ResultModel(TransactionStatus.FAILURE, "Card not found.");
		}
		if (CardStatus.BLOCKED.equals(card.getCardStatus())) {
			return new ResultModel(TransactionStatus.FAILURE, "Blocked Card.");
		}
		Token t = new Token(card, token.getToken(), token.getExpiryDate());
		tokenRepository.save(t);
		return new ResultModel(TransactionStatus.SUCCESS, "Success.");
	}

}
