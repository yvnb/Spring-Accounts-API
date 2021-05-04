package com.nagarro.auth.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.nagarro.auth.domain.Account;
import com.nagarro.auth.repository.AccountRepository;


@Repository
@Qualifier("accountRepository")
public class JdbcAccountRepository implements AccountRepository {

	private static final String SELECT_ACCOUNT_QUERY = "select ID, account_type, account_number from account where ID =:id";
    
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    
	@Override
	public Account findByAccountId(Long id) {
		return jdbcTemplate.queryForObject(
				SELECT_ACCOUNT_QUERY,
                new MapSqlParameterSource("id", id),
                (rs, rowNum) ->
                        new Account(
                                rs.getLong("ID"),
                                rs.getString("account_type"),
                                rs.getString("account_number")
                        ));
	}
}
