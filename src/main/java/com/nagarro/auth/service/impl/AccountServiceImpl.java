package com.nagarro.auth.service.impl;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.nagarro.auth.domain.Account;
import com.nagarro.auth.domain.AccountStatementResponse;
import com.nagarro.auth.domain.Statement;
import com.nagarro.auth.repository.AccountRepository;
import com.nagarro.auth.repository.StatementRepository;
import com.nagarro.auth.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Qualifier("accountService")
public class AccountServiceImpl implements AccountService{

	private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);
	
	@Autowired
	StatementRepository statementRepository;

	@Autowired
	AccountRepository accountRepository;

	@Override
	public AccountStatementResponse getAccStatement(Long id, Date fromDate, Date toDate, BigDecimal fromAmt,
			BigDecimal toAmt) {
		logger.info("Parameters Received from Controller : {}", () -> id);
		//fetch the Statements for the account id
		List<Statement> statements = statementRepository.findByAccountId(id);
		List<Statement> filteredStmts = filterStatementsByDateAndAmount(statements, fromDate, toDate, fromAmt, toAmt);
		logger.info("getting the statements for the id : {}", () -> statements);
		Account account = accountRepository.findByAccountId(id);
		try {
			account.setAccountNumber(getSHAString(account.getAccountNumber()));
		} catch (NoSuchAlgorithmException e) {
			logger.error("Exception while hashing the account number {} ", account.getAccountNumber() );	
		}
		return new AccountStatementResponse(account, filteredStmts);
	}

	private List<Statement> filterStatementsByDateAndAmount(List<Statement> stmts, Date fromDate, Date toDate, BigDecimal fromAmt,
			BigDecimal toAmt){
		
		return stmts.stream().filter(statement->isDateWithinRange(statement.getStmtDate(), fromDate, toDate ))
							 .filter(statement ->isAmountWithinRange(statement.getAmount(), fromAmt, toAmt))
					  .collect(Collectors.toList());
	}

	private boolean isDateWithinRange(Date stmtDate, Date fromDate, Date toDate) {
		
		if(fromDate != null && toDate !=null) {
			return stmtDate.compareTo(fromDate) >=0 &&
					stmtDate.compareTo(toDate) <=0;
		}else {
			if(fromDate !=null) {
				toDate = new Date();
				return stmtDate.compareTo(fromDate) >=0 &&
						stmtDate.compareTo(toDate) <=0;
			} else if (toDate !=null) {
				return stmtDate.compareTo(toDate) <=0;
			}else {
				//if both dates are null, then set the dates from 3 months
				toDate = new Date();
				Calendar cal = Calendar.getInstance(); 
				cal.add(Calendar.MONTH, -3);
				fromDate = cal.getTime();
				return stmtDate.compareTo(fromDate) >=0 &&
						stmtDate.compareTo(toDate) <=0;
			}
		}
	}
	

	private boolean isAmountWithinRange(BigDecimal amount, BigDecimal fromAmt, BigDecimal toAmt) {
		if(fromAmt != null && toAmt !=null) {
			return amount.compareTo(fromAmt) >=0 && amount.compareTo(toAmt) <=0;
		}else {
			if(fromAmt !=null) {
				return amount.compareTo(fromAmt) >=0;
			} else if (toAmt !=null) {
				return amount.compareTo(toAmt) <=0;
			}else {
				return true;
			}
		}
		
	}
	
	private String getSHAString(String field) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(
				field.getBytes(StandardCharsets.UTF_8));
		return bytesToHex(encodedhash);
	}
	
	private String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}

}
