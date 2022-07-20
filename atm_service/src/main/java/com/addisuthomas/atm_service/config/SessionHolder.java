package com.addisuthomas.atm_service.config;

import com.addisuthomas.atm_lib.model.CardModel;

public class SessionHolder {
	private static SessionHolder instance = new SessionHolder();

	private CardModel card;
	private String Token;

	public SessionHolder() {
	}

	public static SessionHolder getInstance() {
		return instance;
	}

	public CardModel getCard() {
		return card;
	}

	public void setCard(CardModel card) {
		this.card = card;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

}
