package com.addisuthomas.bank_service.config;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.addisuthomas.atm_lib.model.CardModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl("/card/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			CardModel card = new ObjectMapper().readValue(request.getInputStream(), CardModel.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(card.getCardNumber(),
					card.getAuthVal(), new ArrayList<>()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication authentication) {

		String token = Jwts.builder().setSubject(String.valueOf(authentication.getPrincipal()))
				.setExpiration(new Date(System.currentTimeMillis() + 604_800_000))
				.signWith(Keys.secretKeyFor(SignatureAlgorithm.HS512))
				.compact();
		


		response.addHeader("Authorization", "Bearer " + token);
	}
}
