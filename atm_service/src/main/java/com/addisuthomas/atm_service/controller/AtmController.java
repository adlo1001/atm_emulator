package com.addisuthomas.atm_service.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.addisuthomas.atm_lib.misc.CardStatus;
import com.addisuthomas.atm_lib.model.CardModel;
import com.addisuthomas.atm_lib.model.ResultModel;
import com.addisuthomas.atm_lib.model.TokenModel;
import com.addisuthomas.atm_lib.model.TransactionModel;
import com.addisuthomas.atm_service.config.SessionHolder;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class AtmController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${bank_service_url}")
	private String url;

	@GetMapping("/card/validateCardNumber/{cardNo}")
	@CircuitBreaker(name = "default", fallbackMethod = "validateCardFallBack")
	// @RateLimiter(name="default")
	public CardStatus validateCardNumber(@PathVariable @NotNull String cardNo) {
		SessionHolder.getInstance()
				.setCard(restTemplate.getForObject(url + "/card/validateCardNumber/" + cardNo, CardModel.class));
		return SessionHolder.getInstance().getCard().getCardStatus();
	}

	public CardStatus validateCardFallBack(Exception ex) {
		return CardStatus.VALID;
	}

	@PostMapping("/card/chooseAuthMethod/cardNo/{cardNo}/auth/{preferredAuth}")
	public ResultModel ChooseAuthMethod(@PathVariable @NotNull String cardNo,
			@PathVariable @NotNull int preferredAuth) {
		if (SessionHolder.getInstance().getCard().getCardStatus().equals(CardStatus.BLOCKED)
				|| SessionHolder.getInstance().getCard().getNumberOfLoginTries() == 3) {
			throw new RuntimeException("BLOCKED CARD!");
		}

		return restTemplate.postForObject(url + "/card/chooseAuthMethod/cardNo/" + cardNo + "/auth/" + preferredAuth,
				null, ResultModel.class);

	}

	@PostMapping("/card/login/{authInput}")
	public void login(@PathVariable @NotNull String authInput, HttpServletRequest request) {
	
		if (SessionHolder.getInstance().getCard().getCardStatus().equals(CardStatus.BLOCKED)
				|| SessionHolder.getInstance().getCard().getNumberOfLoginTries() == 3) {
			throw new RuntimeException("BLOCKED CARD!");
		}

		
		SessionHolder.getInstance().getCard().setAuthVal(authInput);
		HttpEntity<String> response = restTemplate.exchange(url + "/card/login", HttpMethod.POST,
				new HttpEntity<>(SessionHolder.getInstance().getCard()), String.class);
		List<String> authorization = response.getHeaders().get("Authorization");
		SessionHolder.getInstance().setToken(authorization.size() > 0 ? authorization.get(0) : null);
		SessionHolder.getInstance().setCard(new CardModel());
		restTemplate.postForObject(url + "/token/", new TokenModel(SessionHolder.getInstance().getToken(),
				SessionHolder.getInstance().getCard().getCardNumber(), new Date()), ResultModel.class);

	}

	@PostMapping("deposit")
	public ResultModel deposit(@RequestBody TransactionModel model) {
		return restTemplate.postForObject(url + "/account/deposit", model, ResultModel.class);
	}

	@PostMapping("withdraw")
	public ResultModel withdraw(@RequestBody ResultModel model) {
		return restTemplate.postForObject(url + "/account/withdraw", model, ResultModel.class);
	}

	@GetMapping("checkBalance/{cardNo}")
	public ResultModel checkBalance(@PathVariable String cardNo) {
		return restTemplate.getForObject(url + "/account/balance/" + cardNo, ResultModel.class);
	}

}
