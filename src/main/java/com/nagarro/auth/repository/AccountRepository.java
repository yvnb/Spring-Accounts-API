package com.nagarro.auth.repository;


import com.nagarro.auth.domain.Account;


public interface AccountRepository {
	
	Account findByAccountId(Long id);
}
