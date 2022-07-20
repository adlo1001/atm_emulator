package com.addisuthomas.bank_service.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.addisuthomas.atm_lib.misc.AuthChoice;
import com.addisuthomas.atm_lib.misc.CardStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Card {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="card_id")
	private Long id;
	
	@Pattern(regexp = "[\\d]")
	@Column(unique = true, nullable = false)
	private String cardNumber;

	@NotNull
	private CardStatus cardStatus;

	@ManyToOne
	@JoinColumn(name = "account_id", insertable = false, updatable = false, nullable = false)
	private Account account;

	@Max(3)
	private byte loginTries;

	@NotNull
	private AuthChoice authChoice;

	@NotNull
	private String authInput;

	@OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Token> tokens;

	public Card() {

	}

	public Card(@Pattern(regexp = "[\\d]") String cardNumber, @NotNull CardStatus cardStatus, Account account,
			@Max(3) byte loginTries, @NotNull AuthChoice authChoice, @NotNull String authInput, Set<Token> tokens) {
		super();
		this.cardNumber = cardNumber;
		this.cardStatus = cardStatus;
		this.account = account;
		this.loginTries = loginTries;
		this.authChoice = authChoice;
		this.authInput = authInput;
		this.tokens = tokens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public byte getLoginTries() {
		return loginTries;
	}

	public void setLoginTries(byte loginTries) {
		this.loginTries = loginTries;
	}

	public AuthChoice getAuthChoice() {
		return authChoice;
	}

	public void setAuthChoice(AuthChoice authChoice) {
		this.authChoice = authChoice;
	}

	public String getAuthInput() {
		return authInput;
	}

	public void setAuthInput(String authInput) {
		this.authInput = authInput;
	}

	public Set<Token> getTokens() {
		return tokens;
	}

	public void setTokens(Set<Token> tokens) {
		this.tokens = tokens;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", cardNumber=" + cardNumber + ", cardStatus=" + cardStatus + ", account=" + account
				+ ", loginTries=" + loginTries + ", authChoice=" + authChoice + ", authInput=" + authInput + ", tokens="
				+ tokens + "]";
	}

}
