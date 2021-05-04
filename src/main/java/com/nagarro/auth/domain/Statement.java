package com.nagarro.auth.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Statement {

	private Long id;
	private Long accountId;
	private Date stmtDate;
	private BigDecimal amount;
}
