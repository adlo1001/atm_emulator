package com.addisuthomas.bank_service.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.addisuthomas.atm_lib.misc.Operation;
import com.addisuthomas.atm_lib.misc.TransactionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class TransactionHistory {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="hist_id")
	private Long id;

	@NotNull
	private String cardNumber;

	@NotNull
	private Operation operationType;

	@Nullable
	private Double amount;
	private Date dateTime;
	private TransactionStatus transactionStatus;

	public TransactionHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionHistory(@NotNull String cardNumber, @NotNull Operation operationType, Double amount,
			Date dateTime, TransactionStatus transactionStatus) {
		super();
		this.cardNumber = cardNumber;
		this.operationType = operationType;
		this.amount = amount;
		this.dateTime = dateTime;
		this.transactionStatus = transactionStatus;
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

	public Operation getOperationType() {
		return operationType;
	}

	public void setOperationType(Operation operationType) {
		this.operationType = operationType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@Override
	public String toString() {
		return "TransactionHistory [id=" + id + ", cardNumber=" + cardNumber + ", operationType=" + operationType
				+ ", amount=" + amount + ", dateTime=" + dateTime + ", transactionStatus=" + transactionStatus + "]";
	}

}
