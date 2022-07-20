package com.addisuthomas.bank_service.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "token_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "card_id", nullable = false)
	private Card card;

	private String token;
	private Date expireDate;

	public Token() {
	}

	public Token(Card card, String token, Date expireDate) {
		super();
		this.card = card;
		this.token = token;
		this.expireDate = expireDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
}
