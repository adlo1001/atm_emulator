package com.addisuthomas.bank_service.service;

import com.addisuthomas.atm_lib.model.ResultModel;
import com.addisuthomas.atm_lib.model.TokenModel;

public interface TokenService {
	ResultModel storeToken(TokenModel model);

}
