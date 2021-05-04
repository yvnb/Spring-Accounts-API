package com.nagarro.auth.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountStatementRequest {

	@NotNull(message = "Account Id has to be present")
	private Long id;
	@DateTimeFormat(pattern="MMddyyyy")
	private Date fromDate;
	@DateTimeFormat(pattern="MMddyyyy")
	private Date toDate;
	private BigDecimal fromAmt;
	private BigDecimal toAmt;
	
}
