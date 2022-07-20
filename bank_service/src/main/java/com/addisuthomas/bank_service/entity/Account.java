package com.addisuthomas.bank_service.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Account {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="account_id")
	private Long id;

	@NotNull
	@Pattern(regexp="[\\d]")
	private String accountNumber;
	
	@NotNull
	private Double balance;

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Card> cards;

	public Account() {

	}

	public Account( @NotNull @NotNull @Pattern(regexp = "[\\d]") String accountNumber, @NotNull Double balance, Set<Card> cards) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.cards = cards;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public @NotNull @Pattern(regexp = "[\\d]") String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(@NotNull @Pattern(regexp = "[\\d]") String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", balance=" + balance + ", cards=" + cards
				+ "]";
	}
	
	

}
