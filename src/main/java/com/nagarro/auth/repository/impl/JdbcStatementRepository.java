package com.nagarro.auth.repository.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.nagarro.auth.domain.Statement;
import com.nagarro.auth.repository.StatementRepository;



@Repository
@Qualifier("statementRepository")
public class JdbcStatementRepository implements StatementRepository {

    private static final String SELECT_ALL_QUERY = "select ID, account_id, datefield,amount from statement where account_id =:accountId";
    private static final Logger logger = LogManager.getLogger(JdbcStatementRepository.class);
    
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    
	@Override
	public List<Statement> findByAccountId(Long accountId) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return jdbcTemplate.query(
				SELECT_ALL_QUERY,
                new MapSqlParameterSource("accountId", accountId),
                (rs, rowNum) ->                		
                        {
							try {
								return new Statement(
								        rs.getLong("ID"),
								        rs.getLong("account_id"),
								        format.parse(rs.getString("datefield")),
								        rs.getBigDecimal("amount")
								);
							} catch (ParseException e) {
								logger.error("Exception while parsing the statement date {} ", rs.getString("datefield"));								
							}
							return null;
						}
        );
	} 	
}
