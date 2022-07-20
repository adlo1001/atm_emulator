package com.addisuthomas.atm_lib.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.addisuthomas.atm_lib.misc.AuthChoice;
import com.addisuthomas.atm_lib.misc.CardStatus;

public class CardModel {
	@Pattern(regexp = "[\\d]{16}")
	@NotNull
	private String cardNumber;

	@NotNull
	private CardStatus cardStatus;

	@Pattern(regexp = "[1-3]{1}")
	private int numberOfLoginTries;

	private AuthChoice authPreferred;
	private String authVal;

	public CardModel() {

	}

	public CardModel(@Pattern(regexp = "[\\d]{16}") @NotNull String cardNumber, @NotNull CardStatus cardStatus,
			@Pattern(regexp = "[1-3]{1}") int numberOfLoginTries, AuthChoice authPreferred, String authVal) {

		this.cardNumber = cardNumber;
		this.cardStatus = cardStatus;
		this.numberOfLoginTries = numberOfLoginTries;
		this.authPreferred = authPreferred;
		this.authVal = authVal;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public CardStatus getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(CardStatus cardStatus) {
		this.cardStatus = cardStatus;
	}

	public int getNumberOfLoginTries() {
		return numberOfLoginTries;
	}

	public void setNumberOfLoginTries(int numberOfLoginTries) {
		this.numberOfLoginTries = numberOfLoginTries;
	}

	public AuthChoice getAuthPreferred() {
		return authPreferred;
	}

	public void setAuthPreferred(AuthChoice authPreferred) {
		this.authPreferred = authPreferred;
	}

	public String getAuthVal() {
		return authVal;
	}

	public void setAuthVal(String authVal) {
		this.authVal = authVal;
	}

	@Override
	public String toString() {
		return "CardModel [cardNumber=" + cardNumber + ", cardStatus=" + cardStatus + ", numberOfLoginTries="
				+ numberOfLoginTries + ", authPreferred=" + authPreferred + ", authVal=" + authVal + "]";
	}

}
