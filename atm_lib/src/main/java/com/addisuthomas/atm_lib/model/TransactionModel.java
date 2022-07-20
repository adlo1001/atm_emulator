package com.addisuthomas.atm_lib.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class TransactionModel {
	private String cardNumber;
	private Double amount;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public TransactionModel() {

	}

	public TransactionModel(String cardNumber, Double amount) {
		super();
		this.cardNumber = cardNumber;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "TransactionModel [cardNumber=" + cardNumber + ", amount=" + amount + "]";
	}

}
