package com.addisuthomas.bank_service.service;

import javax.validation.constraints.NotNull;

import com.addisuthomas.atm_lib.model.ResultModel;
import com.addisuthomas.atm_lib.model.TransactionModel;
import com.addisuthomas.bank_service.entity.Account;

public interface AccountService {

	ResultModel withdraw(TransactionModel deposit);

	ResultModel deposit(TransactionModel withdrawl);

	ResultModel checkBalance(@NotNull String cardNumber);
	
	/**
	 * create a sample cards
	 */
	 
	void create();

}
