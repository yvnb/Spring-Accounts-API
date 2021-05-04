package com.nagarro.auth.controllers;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.auth.domain.AccountStatementResponse;
import com.nagarro.auth.service.AccountService;



@RestController
@RequestMapping("/api/account")
@Validated
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@GetMapping(value = "/statement", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AccountStatementResponse fetchAccStatement(@RequestParam(value="id") Long id,
			@RequestParam(required = false, value="fromDate")	@DateTimeFormat(pattern="ddMMyyyy") Date fromDate,
			@RequestParam(required = false, value="toDate")	 @DateTimeFormat(pattern="ddMMyyyy") Date toDate,
			@RequestParam(required = false, value="frmAmt")	BigDecimal fromAmt,
			@RequestParam(required = false, value="toAmt")	BigDecimal toAmt){
		return accountService.getAccStatement(id, fromDate, toDate, fromAmt, toAmt);
	}	
}
