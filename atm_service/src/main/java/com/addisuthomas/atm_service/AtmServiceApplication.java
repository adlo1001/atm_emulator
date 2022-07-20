package com.addisuthomas.atm_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ATM API", version = "2.0", description = "EGS- ATM API Information"))
public class AtmServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmServiceApplication.class, args);
	}

}
