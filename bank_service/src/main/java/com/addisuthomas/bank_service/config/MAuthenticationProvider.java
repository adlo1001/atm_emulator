package com.addisuthomas.bank_service.config;

import java.util.ArrayList;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.addisuthomas.atm_lib.misc.CardStatus;
import com.addisuthomas.bank_service.entity.Card;
import com.addisuthomas.bank_service.respository.CardRepository;

@Component
public class MAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CardRepository cardRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String cardNumber = authentication.getName();
		String auth = authentication.getCredentials().toString();

		if (Objects.nonNull(cardNumber) && Objects.nonNull(auth)) {
			Card card = cardRepository.findByCardNumber(cardNumber);
			if (card.getLoginTries() == 3 || CardStatus.BLOCKED.equals(card.getCardStatus())) {
				throw new LockedException("BLOCKED CARD!");
			}

		
			if (!card.getAuthInput().equals(auth)) {
				card.setLoginTries((byte) (card.getLoginTries() + 1));
				if (card.getLoginTries() == 3)
					card.setCardStatus(CardStatus.BLOCKED);
				cardRepository.save(card);
				throw new BadCredentialsException("Wrong Input" + (3 - card.getLoginTries()) + " tries left.");
			}
			return new UsernamePasswordAuthenticationToken(cardNumber, auth, new ArrayList<>());
		} else {
			throw new BadCredentialsException("Card number needed.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
