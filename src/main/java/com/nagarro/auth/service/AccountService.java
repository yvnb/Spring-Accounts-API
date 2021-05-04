package com.nagarro.auth.service;

import java.math.BigDecimal;
import java.util.Date;
import com.nagarro.auth.domain.AccountStatementResponse;



public interface AccountService {

	public AccountStatementResponse getAccStatement(Long id, Date fromDate, Date toDate, BigDecimal frmAmt, BigDecimal toAmt);

}
