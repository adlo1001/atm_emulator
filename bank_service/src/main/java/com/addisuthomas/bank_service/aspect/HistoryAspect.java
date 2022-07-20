package com.addisuthomas.bank_service.aspect;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.addisuthomas.atm_lib.misc.Operation;
import com.addisuthomas.atm_lib.model.ResultModel;
import com.addisuthomas.atm_lib.model.TransactionModel;
import com.addisuthomas.bank_service.entity.TransactionHistory;
import com.addisuthomas.bank_service.respository.TransactionHistoryRepository;

@Aspect
@Component
public class HistoryAspect {

	@Autowired
	private TransactionHistoryRepository historyRepository;

	@AfterReturning(pointcut = "execution(* com.addisuthomas.bank_service.serviceImpl.AccountServiceImpl.deposit(..)) && args(model,..)", returning = "result")
	public void logDeposit(JoinPoint joinPoint, TransactionModel model, ResultModel result) throws Throwable {

		TransactionHistory transHistory = new TransactionHistory();
		transHistory.setCardNumber(model.getCardNumber());
		transHistory.setOperationType(Operation.DEPOSIT);
		transHistory.setAmount(model.getAmount());
		transHistory.setDateTime(new Date());
		transHistory.setTransactionStatus(result.getTransactionStatus());
		historyRepository.save(transHistory);
	}

	@AfterReturning(pointcut = "execution(* com.addisuthomas.bank_service.serviceImpl.AccountServiceImpl.withdraw(..)) && args(model,..)", returning = "result")
	public void logWithdraw(JoinPoint joinPoint, TransactionModel model, ResultModel result) throws Throwable {

		TransactionHistory transHistory = new TransactionHistory();
		transHistory.setCardNumber(model.getCardNumber());
		transHistory.setOperationType(Operation.CHECK_BALANCE);
		transHistory.setAmount(model.getAmount());
		transHistory.setDateTime(new Date());
		transHistory.setTransactionStatus(result.getTransactionStatus());
		historyRepository.save(transHistory);
	}

	@AfterReturning(pointcut = "execution(* com.addisuthomas.bank_service.serviceImpl.AccountServiceImpl.checkBalance(..)) && args(accountNo,..)", returning = "result")
	public void logCheckBalance(JoinPoint joinPoint, String accountNo, ResultModel result) throws Throwable {

		TransactionHistory transHistory = new TransactionHistory();
		transHistory.setCardNumber(accountNo);
		transHistory.setOperationType(Operation.CHECK_BALANCE);
		transHistory.setAmount(null);
		transHistory.setDateTime(new Date());
		transHistory.setTransactionStatus(result.getTransactionStatus());
		historyRepository.save(transHistory);
	}

}
