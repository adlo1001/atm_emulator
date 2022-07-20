package com.addisuthomas.bank_service.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.addisuthomas.atm_lib.model.CardModel;
import com.addisuthomas.atm_lib.model.ResultModel;
import com.addisuthomas.atm_lib.model.TransactionModel;
import com.addisuthomas.bank_service.entity.Account;
import com.addisuthomas.bank_service.service.AccountService;
import com.addisuthomas.bank_service.service.CardService;

import io.github.resilience4j.retry.annotation.Retry;



@RestController
public class BankController {

	@Autowired
	private CardService cardService;
	@Autowired
	private AccountService accountService;

	@PostMapping("/account/deposit")
	//@Retry(name = "bank-api", fallbackMethod = "deposit")
	public ResponseEntity<ResultModel> deposit(@RequestBody TransactionModel model) {
		return ResponseEntity.ok(accountService.deposit(model));
	}

	@PostMapping("/account/withdraw")
	public ResponseEntity<ResultModel> withdraw(@RequestBody TransactionModel model) {
		return ResponseEntity.ok(accountService.withdraw(model));
	}

	@GetMapping("/account/balance/{cardNumber}")
	public ResponseEntity<ResultModel> checkBalance(@PathVariable @NotNull String cardNumber) {
		return ResponseEntity.ok(accountService.checkBalance(cardNumber));
	}

	@GetMapping("/card/validateCardNumber/{cardNo}")
	public ResponseEntity<CardModel> validateCardNumber(@PathVariable @NotNull String cardNo) {
		return ResponseEntity.ok(cardService.validateCardNumber(cardNo));
	}

	@PostMapping("/card/chooseAuthMethod/cardNo/{cardNo}/auth/{preferredAuth}")
	public ResponseEntity<CardModel> chooseAuthMethod(@PathVariable @NotNull String cardNo,
			@PathVariable @NotNull String preferredAuth) {
		return ResponseEntity.ok(cardService.chooseAuthMethod(cardNo, preferredAuth));
	}
	@PostMapping("/token")
	public ResponseEntity<ResultModel> storeToken(@RequestBody TokenModel model) {
		return ResponseEntity.ok(accountService.withdraw(model));
	}
}
