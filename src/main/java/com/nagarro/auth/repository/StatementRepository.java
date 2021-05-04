package com.nagarro.auth.repository;

import java.util.List;
import com.nagarro.auth.domain.Statement;

public interface StatementRepository {
	
	List<Statement> findByAccountId(Long accountId);
}
