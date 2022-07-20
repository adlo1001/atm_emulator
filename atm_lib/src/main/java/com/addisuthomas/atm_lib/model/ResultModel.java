package com.addisuthomas.atm_lib.model;

import com.addisuthomas.atm_lib.misc.Operation;
import com.addisuthomas.atm_lib.misc.TransactionStatus;

import lombok.*;

public class ResultModel {
	private TransactionStatus transactionStatus;
	private String message;

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResultModel(TransactionStatus transactionStatus, String message) {
		this.transactionStatus = transactionStatus;
		this.message = message;
	}

	public ResultModel() {

	}

	@Override
	public String toString() {
		return "ResultModel [transactionStatus=" + transactionStatus + ", message=" + message + "]";
	}

}