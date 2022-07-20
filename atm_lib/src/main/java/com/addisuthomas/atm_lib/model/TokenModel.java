package com.addisuthomas.atm_lib.model;

import java.util.Date;

public class TokenModel {

	private String token;
	private String cardNumber;
	private Date ExpiryDate;

	public TokenModel() {
	}

	public TokenModel(String token, String cardNumber, Date expiryDate) {
		super();
		this.token = token;
		ExpiryDate = expiryDate;
		this.cardNumber = cardNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return ExpiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		ExpiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "TokenModel [token=" + token + ", ExpiryDate=" + ExpiryDate + "]";
	}

}
