package com.addisuthomas.bank_service.serviceImpl;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addisuthomas.atm_lib.misc.AuthChoice;
import com.addisuthomas.atm_lib.misc.CardStatus;
import com.addisuthomas.atm_lib.misc.TransactionStatus;
import com.addisuthomas.atm_lib.model.ResultModel;
import com.addisuthomas.atm_lib.model.TransactionModel;
import com.addisuthomas.bank_service.entity.Account;
import com.addisuthomas.bank_service.entity.Card;
import com.addisuthomas.bank_service.entity.Token;
import com.addisuthomas.bank_service.respository.AccountRepository;
import com.addisuthomas.bank_service.respository.CardRepository;
import com.addisuthomas.bank_service.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public ResultModel deposit(TransactionModel model) {
		Card card = cardRepository.findByCardNumber(model.getCardNumber());
		if (Objects.isNull(card)) {
			return new ResultModel(TransactionStatus.FAILURE, "Card not found.");
		}
		if (CardStatus.BLOCKED.equals(card.getCardStatus())) {
			return new ResultModel(TransactionStatus.FAILURE, "Blocked Card.");
		}

		Account account = card.getAccount();
		account.setBalance(account.getBalance() + model.getAmount());
		accountRepository.save(account);
		return new ResultModel(TransactionStatus.SUCCESS, "Success.");
	}

	@Override
	public ResultModel withdraw(TransactionModel model) {
		Card card = cardRepository.findByCardNumber(model.getCardNumber());
		if (Objects.isNull(card)) {
			return new ResultModel(TransactionStatus.FAILURE, "Card not found.");
		}

		if (CardStatus.BLOCKED.equals(card.getCardStatus())) {
			return new ResultModel(TransactionStatus.FAILURE, "Blocked Card.");
		}

		Account account = card.getAccount();
		if (account.getBalance().compareTo(model.getAmount()) < 0) {
			return new ResultModel(TransactionStatus.FAILURE, "insufficient Balance.");
		}
		account.setBalance(account.getBalance() - model.getAmount());
		accountRepository.save(account);
		return new ResultModel(TransactionStatus.SUCCESS, "Success.");

	}

	@Override
	public ResultModel checkBalance(@NotNull String cardNumber) {

		Card card = cardRepository.findByCardNumber(cardNumber);
		if (Objects.isNull(card)) {
			return new ResultModel(TransactionStatus.FAILURE, "Card not found.");
		}
		if (CardStatus.BLOCKED.equals(card.getCardStatus())) {
			return new ResultModel(TransactionStatus.FAILURE, "Blocked Card.");
		}
		Account account = card.getAccount();
		return new ResultModel(TransactionStatus.SUCCESS, String.valueOf(account.getBalance()));
	}

	@Override
	public void create() {
		Set<Card> cards = new HashSet<>();
		Account account = new Account("7021020007665016", 1000.00, cards);
		accountRepository.save(account);

	}

}
